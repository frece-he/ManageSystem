package web.frece.service;


import net.sf.json.JSONObject;

public interface LoginService {
	public JSONObject dologin(String userName, String password) throws Exception;
}
