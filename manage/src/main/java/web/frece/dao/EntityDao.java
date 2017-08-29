package web.frece.dao;

import java.util.Map;

import org.bson.conversions.Bson;

import net.sf.json.JSONObject;

public interface EntityDao {
	public JSONObject findAll(String database, String collection)throws Exception;
	public JSONObject getEntity(String database, String collection, Map<String, Object> param)throws Exception;
	public JSONObject doSearch(String database, String collection, Bson filter)throws Exception;
}
