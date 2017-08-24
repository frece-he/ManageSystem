package web.frece.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import web.frece.dao.EntityDao;
import web.frece.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Resource
	private EntityDao entityDao;
	
	@Override
	public JSONObject dologin(String userName, String password) throws Exception {
		String index = "login";
		String type = "";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userName", userName);
		param.put("password", password);
		JSONObject res = entityDao.getEntity(index, type, param);
		return res;
	}

	
}
