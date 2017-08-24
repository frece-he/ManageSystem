package web.frece.dao.impl;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.sf.json.JSONObject;
import web.frece.dao.EntityDao;


@Repository
public class EntityDaoImpl implements EntityDao {

	@Override
	public JSONObject getEntity(String index, String type, Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return JSONObject.fromObject(param);
	}
	
	


}
