package web.frece.dao;

import java.util.Map;

import net.sf.json.JSONObject;

public interface EntityDao {
	public JSONObject getEntity(String index, String type, Map<String, Object> param)throws Exception;
}
