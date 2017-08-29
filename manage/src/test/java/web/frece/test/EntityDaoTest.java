package web.frece.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.sf.json.JSONObject;
import web.frece.dao.EntityDao;
import web.frece.util.ConstantsData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:configs/spring-mvc.xml" })
public class EntityDaoTest {
	@Resource
	private EntityDao entityDao;
	
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
