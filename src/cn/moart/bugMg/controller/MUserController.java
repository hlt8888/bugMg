package cn.moart.bugMg.controller;

import java.util.List;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.bean.MUser;
import cn.moart.bugMg.service.MUserService;

@Controller
public class MUserController {
	@Autowired
	private MUserService service;
	
	@RequestMapping("/users")
	public @ResponseBody List<MUser> getAllUser(){
		return service.getAll();
	}
	
	@RequestMapping("/user/searchJSON")
	public @ResponseBody JSONPObject autoComUserInfo(int maxRows, String name_startsWith, String callback){
		List<MUser> list = service.autoComUserInfo(maxRows, name_startsWith);
		return new JSONPObject(callback, list);
	}
}
