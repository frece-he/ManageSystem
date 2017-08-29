package web.frece.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import web.frece.dao.EntityDao;
import web.frece.service.LoginService;
import web.frece.util.ConstantsData;
import web.frece.util.ConstantsField;

@Service
public class LoginServiceImpl implements LoginService {

	@Resource
	private EntityDao entityDao;
	
	@Override
	public JSONObject dologin(String userName, String password) throws Exception {
		JSONObject res = new JSONObject();
		String database = ConstantsData.DATABASE;
		String collection = ConstantsData.TABLE_LOGIN;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ConstantsField.Login_username, userName);
		param.put(ConstantsField.Login_password, password);
		JSONObject temp = entityDao.getEntity(database, collection, param);
		if(null != temp && (int)temp.get(ConstantsData.JSONLIST_SIZE) != 0) {
			res.put("userName", userName);
		}
		return res;
	}

	
}
