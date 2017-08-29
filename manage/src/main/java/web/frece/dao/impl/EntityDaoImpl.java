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
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import net.sf.json.JSONObject;
import web.frece.dao.EntityDao;
import web.frece.util.ConstantsData;
import web.frece.util.Converter;


@Repository(value="entityDao")
public class EntityDaoImpl implements EntityDao {

	@Autowired
	private MongoClient client ;

	@Override
	public JSONObject findAll(String database, String collection) throws Exception {
		JSONObject result = new JSONObject();

		FindIterable<Document> findIterable =  client.getDatabase(database).getCollection(collection).find();  
		MongoCursor<Document> mongoCursor = findIterable.iterator();  
		List<JSONObject> data = new ArrayList<JSONObject>();
		while(mongoCursor.hasNext()){  
			data.add( Converter.documentToJson(mongoCursor.next()));
		}  
		result.put(ConstantsData.JSONLIST_RESULT, data);
		result.put(ConstantsData.JSONLIST_SIZE, data.size());
		return result;
	}


	@Override
	public JSONObject getEntity(String database, String collection, Map<String, Object> param) throws Exception {
		
		Set<String> keySet = param.keySet();
		Bson[] filter = new Bson[keySet.size()];
		int i = 0;
		for (String key : keySet) {
			filter[i] = Filters.eq(key, param.get(key));
			i++;
		}
		
		JSONObject result = new JSONObject();
		FindIterable<Document> findIterable =  client.getDatabase(database).getCollection(collection)
				.find(Filters.and(filter));
		MongoCursor<Document> mongoCursor = findIterable.iterator();  
		List<JSONObject> data = new ArrayList<JSONObject>();
		while(mongoCursor.hasNext()){  
			data.add( Converter.documentToJson(mongoCursor.next()));
		}  
		result.put(ConstantsData.JSONLIST_RESULT, data);
		result.put(ConstantsData.JSONLIST_SIZE, data.size());
		return result;
	}


	@Override
	public JSONObject doSearch(String database, String collection, Bson filter) throws Exception {
		
		JSONObject result = new JSONObject();
		FindIterable<Document> findIterable =  client.getDatabase(database).getCollection(collection)
				.find(filter);
		MongoCursor<Document> mongoCursor = findIterable.iterator();  
		List<JSONObject> data = new ArrayList<JSONObject>();
		while(mongoCursor.hasNext()){  
			data.add( Converter.documentToJson(mongoCursor.next()));
		}  
		result.put(ConstantsData.JSONLIST_RESULT, data);
		result.put(ConstantsData.JSONLIST_SIZE, data.size());
		
		return result;
	}






}
