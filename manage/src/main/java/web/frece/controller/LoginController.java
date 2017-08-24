package web.frece.controller;


import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import web.frece.service.LoginService;
import web.frece.util.ResultModel;

@Controller
@RequestMapping("/login")
public class LoginController  implements Serializable {

	private static final long serialVersionUID = -4805067839693640057L;
	@Resource
	private LoginService loginService;


	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	@ResponseBody()
	public ResultModel doLogin(@RequestParam Map data, HttpServletRequest request) {
		ResultModel res = new ResultModel();
		System.out.println("in doLogin Function");
		
		if(data != null) {
			String userName = (String) data.get("userName");
			String password = (String) data.get("password");
			userName = null == userName? "nullName":userName;
			password = null == password? "nullPass":password;
			System.out.println("userName: " + userName + ", password: " + password);
			try {
				JSONObject resjson = loginService.dologin(userName, password);
				
				res.setResultCode("200");
				res.setResultData(resjson);
				res.setResultMsg("success");
			} catch (Exception e) {
				e.printStackTrace();
				res.setResultCode("500");
			}
		}		
		System.out.println("Return param: " + res.toString());
		return res;		
	}

}
