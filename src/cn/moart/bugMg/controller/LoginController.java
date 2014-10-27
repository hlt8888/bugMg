package cn.moart.bugMg.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService service;
	
	@RequestMapping("/views/login")
	public @ResponseBody Map<String, String> login(String email, String pwd, HttpSession session) {
		Map<String, String> rs = new HashMap<String, String>();
		Map<String, Object> map = service.getMUserByEmailAndPwd(email, pwd);
		
		if(map!=null){
			rs.put("flag", "OK");
			session.setAttribute("user", map);
		} else {
			rs.put("flag", "NG");
		}
		return rs;
	}
}