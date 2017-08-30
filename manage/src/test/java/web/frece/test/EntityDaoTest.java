package web.frece.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.client.model.Filters;

import web.frece.dao.EntityDao;
import web.frece.util.ConstantsData;
import web.frece.util.ConstantsField;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:configs/spring-mvc.xml" })
@SuppressWarnings("unchecked")
public class EntityDaoTest {
	@Resource
	private EntityDao entityDao;
	
	@Test
	public void testDB() {
		try {
			List<Document> jsonList =  (List<Document>) entityDao.findAll(ConstantsData.TABLE_LOGIN).get(ConstantsData.RTN_RESULT);
			for (Document row : jsonList) {
				System.out.println(row);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Test
	public void testCreateEntityStringMapOfStringObject() throws Exception {
		Map<String, Object> entity = new HashMap<String, Object>();
//		entity.put(ConstantsField.ID, "frece");
		entity.put(ConstantsField.Login_username, "frece");
		entity.put(ConstantsField.Login_password, "123");
		String res = entityDao.createEntity(ConstantsData.TABLE_LOGIN, entity);
		System.out.println(res);
	}

	@Test
	public void testCreateEntityStringDocument()throws Exception {
		Document entity = new Document();
//		entity.put(ConstantsField.ID, "henry");
		entity.put(ConstantsField.Login_username, "henry");
		entity.put(ConstantsField.Login_password, "123");
		String res = entityDao.createEntity(ConstantsData.TABLE_LOGIN, entity);
		System.out.println(res);
	}

	@Test
	public void testDoUpdateStringBsonMapOfStringObject()throws Exception {
		Bson filter = Filters.eq(ConstantsField.Login_username,"frece");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ConstantsField.Login_password, "123456");
		
		Document res = entityDao.doUpdate(ConstantsData.TABLE_LOGIN, filter, param);
		System.out.println(res);
	}

	@Test
	public void testDoUpdateStringBsonDocument()throws Exception {
		Bson filter = Filters.eq(ConstantsField.Login_username,"henry");
		
		Document param = new Document();
		param.put(ConstantsField.Login_password, "1234");
		
		Document res = entityDao.doUpdate(ConstantsData.TABLE_LOGIN, filter, param);
		System.out.println(res);
	}

	@Test
	public void testFindAll()throws Exception {
		Document res = entityDao.findAll(ConstantsData.TABLE_LOGIN);
		
		List<Document> resList = (List<Document>) res.get(ConstantsData.RTN_RESULT);
		for (Document document : resList) {
			System.out.println(document.toJson());
		}
		
		
	}


	@Test
	public void testGetEntity()throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ConstantsField.Login_username, "henry");
		Document res =  entityDao.getEntities(ConstantsData.TABLE_LOGIN, param);
		
		List<Document> resList = (List<Document>) res.get(ConstantsData.RTN_RESULT);
		for (Document document : resList) {
			System.out.println(document.toJson());
		}
	}

	@Test
	public void testDoSearchStringBson()throws Exception {

		Bson filter = Filters.eq(ConstantsField.Login_username, "henry");
		
		Document res =  entityDao.doSearch(ConstantsData.TABLE_LOGIN, filter);
		
		List<Document> resList = (List<Document>) res.get(ConstantsData.RTN_RESULT);
		for (Document document : resList) {
			System.out.println(document.toJson());
		}
	}

	@Test
	public void testDoSearchStringStringBson() throws Exception{

		Bson filter = Filters.ne(ConstantsField.Login_username, "frece");
		
		Document res =  entityDao.doSearch(ConstantsData.DEFAULT_DB, ConstantsData.TABLE_LOGIN, filter);
		
		List<Document> resList = (List<Document>) res.get(ConstantsData.RTN_RESULT);
		for (Document document : resList) {
			System.out.println(document.toJson());
		}
	}
	
	
	
	
	@Test
	public void testRemoveEntity() throws Exception {
		Bson filter = Filters.eq(ConstantsField.Login_username, "henry");
		Document res = entityDao.removeEntities(ConstantsData.TABLE_LOGIN, filter);
		System.out.println(res);
	}
	
	@Test
	public void testRemoveAll() throws Exception  {
		Document res = entityDao.removeAll(ConstantsData.TABLE_LOGIN);
		System.out.println(res);
	}

//	@SuppressWarnings("deprecation")
//	@Test
//	public void testDeleteEntity() throws Exception{
//		Bson filter = Filters.eq(ConstantsField.Login_username, "henry");
//		System.out.println(entityDao.deleteEntity(ConstantsData.TABLE_LOGIN, filter));
//	}
//	
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testDeleteAll() throws Exception{
//		System.out.println(entityDao.deleteAll(ConstantsData.TABLE_LOGIN));
//	}
//	
	
	
	
}
