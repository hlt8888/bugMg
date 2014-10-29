package cn.moart.bugMg.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.bean.MBug;
import cn.moart.bugMg.bean.MUser;
import cn.moart.bugMg.bean.PageBean;
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
	List<Map<String, Object>> getAllUser(PageBean page, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
		List<Map<String, Object>> list = null;
		if(user != null){
			page.setBegin();
			page.setEnd();
			list = service.getAll(page);
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
	public String bugAdd(MBug bug, HttpSession session){
		Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
		int userid = 0;
		if(user == null){
			return null;
		}
		if(bug.getName() == null || bug.getContent() == null){
			return "redirect:/views/bug/tonew_bug";
		}
		userid = (Integer)user.get("id");
		bug.setCreatedby(userid);
		bug.setAction("1");
		service.bugAdd(bug);
		return "redirect:/views/bug/tonew_bug";
	}
	
	@RequestMapping("/views/bug/tonew_bug")
	public String toNewbug(ModelMap model){
		List<MUser> listuser = userService.getAll();
		model.addAttribute("listuser", listuser);
		return "/views/bug/bugnew";
	}
	
	@RequestMapping("/aj/bug/getmessages")
	public @ResponseBody String getMessageByBugid(int bug_id){
		
		return null;
	}
}
