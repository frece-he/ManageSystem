package web.frece.dao.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import net.sf.json.JSONObject;
import web.frece.dao.EntityDao;
import web.frece.util.ConstantsData;
import web.frece.util.Converter;


@Repository
public class EntityDaoImpl implements EntityDao {


	@SuppressWarnings("resource")
	@Override
	public JSONObject findAll(String database, String collection) throws Exception {
		JSONObject result = new JSONObject();

		MongoClient client = new MongoClient(ConstantsData.HOST_NAME , ConstantsData.PORT_NUM);
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

		return null;
	}






}
