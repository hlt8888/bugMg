package cn.moart.bugMg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.bean.MBug;
import cn.moart.bugMg.service.MBugService;

@Controller
@RequestMapping("/bugs")
public class MBugController {
	@Autowired
	private MBugService service;
	
	@RequestMapping
	public @ResponseBody List<MBug> getAllUser(){
		return service.getAll();
	}
}
