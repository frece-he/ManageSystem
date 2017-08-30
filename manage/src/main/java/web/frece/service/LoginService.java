package web.frece.service;

import org.bson.Document;

import net.sf.json.JSONObject;

public interface LoginService {
	public JSONObject dologin(String userName, String password) throws Exception;
}
