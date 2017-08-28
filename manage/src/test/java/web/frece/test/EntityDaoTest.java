package web.frece.test;

import java.util.List;
import org.junit.Test;
import net.sf.json.JSONObject;
import web.frece.dao.EntityDao;
import web.frece.dao.impl.EntityDaoImpl;
import web.frece.util.ConstantsData;

public class EntityDaoTest {
	private EntityDao entityDao = new EntityDaoImpl();
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDB() {
		try {
			List<JSONObject> jsonList =  (List<JSONObject>) entityDao.findAll(ConstantsData.DATABASE, ConstantsData.TABLE_LOGIN).get(ConstantsData.JSONLIST_RESULT);
			for (JSONObject row : jsonList) {
				System.out.println(row);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
