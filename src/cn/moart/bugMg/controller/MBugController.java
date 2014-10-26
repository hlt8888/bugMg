package cn.moart.bugMg.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.bean.MBug;
import cn.moart.bugMg.service.MBugService;

@Controller
public class MBugController {
	@Autowired
	private MBugService service;

	@RequestMapping("/views/bug/bugs")
	public @ResponseBody
	List<Map<String, Object>> getAllUser() {
		return service.getAll();
	}
	
	@RequestMapping("/views/bug/bugedit")
	public String bugEdit(int id, ModelMap model) {
		System.out.println("id:"+id);
		Map<String, Object> bug = service.getMBugById(id);
		model.addAttribute("bug",bug);
		return "/views/bug/bugedit";
	}
}
