package web.frece.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bson.Document;

import net.sf.json.JSONObject;

public class Converter {
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(JSONObject json) {
		if(null == json || json.isEmpty() || json.isNullObject()) {
			return null;
		}
		Map<String, Object> resMap = new HashMap<String, Object>();
		Iterator<String> it = json.keys();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value = json.get(key);
			resMap.put(key, value);
		}
		return resMap;
	}
	
	public static Document jsonToDocument(JSONObject json) {
		Document res = null;
		Map<String, Object> map = jsonToMap(json);
		if(null == map) {
			res = null;
		}else {
			res = new Document(map);
		}		
		return res;
	}
	
	public static JSONObject documentToJson(Document  doc) {
		if(null == doc || doc.isEmpty()) {
			return null;
		}else {
			return JSONObject.fromObject(doc.toJson());
		}
		
	}
	
	public static Map<String, Object> documentToMap(Document  doc) {
		if(null == doc || doc.isEmpty()) {
			return null;
		}else {
			return jsonToMap(JSONObject.fromObject(doc.toJson())) ;
		}
		
	}
	
}
