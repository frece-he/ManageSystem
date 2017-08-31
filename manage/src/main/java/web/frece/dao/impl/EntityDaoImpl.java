package web.frece.dao.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import web.frece.dao.EntityDao;
import web.frece.util.CommonUtil;
import web.frece.util.ConstantsData;
import web.frece.util.ConstantsField;


@Repository(value="entityDao")
public class EntityDaoImpl implements EntityDao {

	@Autowired
	private MongoClient client ;
	
	@Autowired
	private MongoDatabase defaultDB;


	@Override
	public String createEntity(String collection, Map<String, Object> entity) throws Exception {
		Document document = new Document(entity);
		return createEntity(collection, document);
	}

	@Override
	public String createEntity(String collection, Document document) throws Exception {
		
		MongoCollection<Document> coln =  defaultDB.getCollection(collection);
		coln.insertOne(document);
		return document.get(ConstantsField.ID).toString();
	}

	@Override
	public void createEntities(String collection, List<Document> documents) throws Exception {
		MongoCollection<Document> coln =  defaultDB.getCollection(collection);
		
		String currentTime = CommonUtil.getCurrentTime();
		for (Document document : documents) {
			document.append(ConstantsField.CREATE_TIME, currentTime);
			document.append(ConstantsField.UPDATE_TIME, currentTime);
			document.append(ConstantsField.IS_DELETED,false);
		}
		
		coln.insertMany(documents);
	}

	@Override
	public Document doUpdate(String collection, Bson filter, Map<String, Object> param) throws Exception {
		Document document = new Document(param);
		return doUpdate(collection, filter, document);
	}



	@Override
	public Document doUpdate(String collection, Bson filter, Document param) throws Exception {
		Document result = new Document();
		param.append(ConstantsField.UPDATE_TIME, CommonUtil.getCurrentTime());		
		MongoCollection<Document> coln =  defaultDB.getCollection(collection);
		filter = Filters.and(filter, Filters.ne(ConstantsField.IS_DELETED, true));
		UpdateResult updRes = coln.updateMany(filter, new Document("$set",param));
		long matchedCount = updRes.getMatchedCount();
		long modifiedCount = updRes.getModifiedCount();
		
		result.append(ConstantsData.isUpdateSuccess, matchedCount == modifiedCount ? true:false);
		result.append(ConstantsData.Matched_Count, matchedCount );
		result.append(ConstantsData.Modified_Count, modifiedCount);
		return result;
	}


	@Override
	public  List<Document> findAll(String collection) throws Exception {
		List<Document> data = new ArrayList<Document>();
		FindIterable<Document> findIterable = defaultDB.getCollection(collection).find();  
		MongoCursor<Document> mongoCursor = findIterable.iterator();		
		while(mongoCursor.hasNext()){  
//			data.add(Converter.documentToJson(mongoCursor.next()));
			data.add(mongoCursor.next());
		}  
		
		return data;
	}

	@Override
	public  List<Document> getEntities(String collection, Map<String, Object> param) throws Exception {
		List<Document> data = new ArrayList<Document>();
		Set<String> keySet = param.keySet();
		Bson[] filter = new Bson[keySet.size() + 1];
		int i = 0;
		for (String key : keySet) {
			filter[i] = Filters.eq(key, param.get(key));
			i++;
		}
		filter[i] = Filters.ne(ConstantsField.IS_DELETED, true);

		FindIterable<Document> findIterable = defaultDB.getCollection(collection)
				.find(Filters.and(filter));
		MongoCursor<Document> mongoCursor = findIterable.iterator();  
		while(mongoCursor.hasNext()){  
//			data.add(Converter.documentToJson(mongoCursor.next()));
			data.add(mongoCursor.next());
		}  
		return data;
	}

	
	@Override
	public  List<Document> doSearch(String collection, Bson filter) throws Exception {
		return doSearch(ConstantsData.DEFAULT_DB,collection, filter);
	}

	@Override
	public  List<Document> doSearch(String database, String collection, Bson filter) throws Exception {	
		List<Document> data = new ArrayList<Document>();
		filter = Filters.and(filter, Filters.ne(ConstantsField.IS_DELETED, true));
		FindIterable<Document> findIterable =  client.getDatabase(database).getCollection(collection)
				.find(filter);
		MongoCursor<Document> mongoCursor = findIterable.iterator();  
		
		while(mongoCursor.hasNext()){  
//			data.add(Converter.documentToJson(mongoCursor.next()));
			data.add(mongoCursor.next());
		}  

		return data;
	}

	@Override
	public Document removeEntities(String collection, Bson filter) throws Exception {
		Document result = new Document();
		Document param = new Document();
		param.append(ConstantsField.IS_DELETED, true);
		param.append(ConstantsField.UPDATE_TIME, CommonUtil.getCurrentTime());
		MongoCollection<Document> coln =  defaultDB.getCollection(collection);
		UpdateResult updRes = coln.updateMany(filter, new Document("$set",param));
		long matchedCount = updRes.getMatchedCount();
		long modifiedCount = updRes.getModifiedCount();
		
		result.append(ConstantsData.isUpdateSuccess, matchedCount == modifiedCount ? true:false);
		result.append(ConstantsData.Matched_Count, matchedCount );
		result.append(ConstantsData.Deleted_Count, modifiedCount);
		return result;
	}

	@Override
	public Document removeAll(String collection) throws Exception {
		Bson filter = Filters.ne(ConstantsField.IS_DELETED, true);
		return removeEntities(collection, filter);
	}


	@Override
	public String deleteEntity(String collection, Bson filter) throws Exception {
		MongoCollection< Document> coln = defaultDB.getCollection(collection);
		DeleteResult delRes = coln.deleteMany(filter);
		
		return "" + delRes.getDeletedCount();
	}

	@Override
	public String deleteAll(String collection) throws Exception {
		MongoCollection< Document> coln = defaultDB.getCollection(collection);
		coln.drop();
		return "droped finished";
	}


	
}
