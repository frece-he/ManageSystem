package web.frece.test;

import org.junit.Test;

import web.frece.util.CommonUtil;

public class CommonTest {

	@Test
	public void commonTest() throws Exception {
		for (int i = 0; i < 1000; i++) {
			if(i < 500) {
				System.out.println(CommonUtil.getCurrentTime());
			}else if(i == 500){
				System.out.println("=================================");
				System.out.println("=================================");
			}else {
				System.out.println(CommonUtil.getCurrentDate());
			}
			
		}
		
	}
}
