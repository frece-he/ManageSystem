package web.frece.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.sf.json.JSONObject;
import web.frece.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:configs/spring-mvc.xml" })
public class LoginTest {
	@Resource
	private LoginService loginService;
	
	@Test
	public void testLogin() {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("username", "frece");
			param.put("password", "123");
//			param.put("username", "hurry");
//			param.put("password", 456);

//			List<JSONObject> jsonList =  (List<JSONObject>) entityDao.getEntity(ConstantsData.DATABASE, ConstantsData.TABLE_LOGIN, param).get(ConstantsData.JSONLIST_RESULT);
//			for (JSONObject row : jsonList) {
//				System.out.println("value: " + row);
//			}
//			System.out.println("size: " + jsonList.size());
			JSONObject res = loginService.dologin("frece", "123");
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
