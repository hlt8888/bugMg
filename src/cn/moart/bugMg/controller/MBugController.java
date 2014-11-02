package cn.moart.bugMg.controller;

import java.util.HashMap;
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
import cn.moart.bugMg.bean.QueryBugBean;
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
	PageBean getAllUser(QueryBugBean query, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
		PageBean page = null;
		if(user != null){
			page = service.getAll(query);
		}
		return page;
	}
	
	@RequestMapping("/views/bug/bugedit")
	public String bugEdit(int id, ModelMap model, HttpSession session) {
		Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
		Map<String, Object> bug = service.getMBugById(id);
		model.addAttribute("bug",bug);
		model.addAttribute("userid",user.get("id"));
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
		return "OK";
	}
	
	@RequestMapping("/views/bug/tonew_bug")
	public String toNewbug(ModelMap model){
		List<MUser> listuser = userService.getAll();
		model.addAttribute("listuser", listuser);
		return "/views/bug/bugnew";
	}
	
	@RequestMapping("/aj/bug/getmessages")
	public @ResponseBody List<Map<String,Object>> getMessageByBugid(int bug_id){
		List<Map<String,Object>> list_message =  service.getMessagesByBugid(bug_id);
		return list_message;
	}
	
	@RequestMapping("/aj/bug/updatebug")
	public @ResponseBody String updateBug(int bug_id, int action, int createdby, HttpSession session){
		Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
		if(user==null){
			return null;
		}
		int userid = (Integer) user.get("id");
		if(userid != createdby){
			return "NG";
		}
		
		Map<String, String> map_set 	= new HashMap<String, String> ();
		Map<String, String> map_where 	= new HashMap<String, String> ();
		map_set.put("action", String.valueOf(action));
		map_where.put("id", String.valueOf(bug_id));
		map_where.put("createdby", String.valueOf(createdby));
		service.updateBug(map_set, map_where);
		return "OK";
	}
}
