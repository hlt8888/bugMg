package cn.moart.bugMg.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.bean.MUser;
import cn.moart.bugMg.service.MBugService;
import cn.moart.bugMg.service.MUserService;

@Controller
public class MBugController {
	@Autowired
	private MBugService service;
	@Autowired
	private MUserService userService;
	@RequestMapping("/views/bug/bugs")
	public @ResponseBody
	List<Map<String, Object>> getAllUser(HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
		List<Map<String, Object>> list = null;
		if(user != null){
			list = service.getAll();
		}
		return list;
	}
	
	@RequestMapping("/views/bug/bugedit")
	public String bugEdit(int id, ModelMap model, HttpSession session) {
		System.out.println("id:"+id);
		Map<String, Object> bug = service.getMBugById(id);
		model.addAttribute("bug",bug);
		return "/views/bug/bugedit";
	}
	
	@RequestMapping("/views/bug/add")
	public String bugAdd(String name, String content, HttpSession session){
		Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
		int userid = 0;
		if(user == null){
			return null;
		}
		userid = (Integer)user.get("id");
		service.bugAdd(name, content, userid);
		return "/views/bug/buglist";
	}
	
	@RequestMapping("/views/bug/tonew_bug")
	public String toNewbug(ModelMap model){
		List<MUser> listuser = userService.getAll();
		model.addAttribute("listuser", listuser);
		return "/views/bug/bugnew";
	}
}
