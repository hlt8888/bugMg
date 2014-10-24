package cn.moart.bugMg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.bean.MUser;
import cn.moart.bugMg.service.MUserService;

@Controller
@RequestMapping("/users")
public class MUserController {
	@Autowired
	private MUserService service;
	
	@RequestMapping
	public @ResponseBody List<MUser> getAllUser(){
		return service.getAll();
	}
}
