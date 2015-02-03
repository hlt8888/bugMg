package cn.moart.bugMg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.bean.MUser;
import cn.moart.bugMg.bean.PageBean;
import cn.moart.bugMg.bean.QueryBugBean;
import cn.moart.bugMg.service.MUserService;

@Controller
public class MUserController {
	@Autowired
	private MUserService service;
	
	@RequestMapping("/views/user/users")
	public @ResponseBody
	PageBean getAllUser(QueryBugBean query, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
		PageBean page = null;
//		if(user != null){
			page = service.getAll(query);
//		}
		return page;
	}
	
	@RequestMapping("/user/searchJSON")
	public @ResponseBody JSONPObject autoComUserInfo(int maxRows, String name_startsWith, String callback){
		List<MUser> list = service.autoComUserInfo(maxRows, name_startsWith);
		return new JSONPObject(callback, list);
	}
	
	@RequestMapping("/user/roles")
	public @ResponseBody List<Map<String,String>> getAllRoles(){
		List<Map<String, String>> list = new ArrayList<Map<String, String>> ();
		Map<String, String> map = new HashMap<String, String> ();
		map.put("roid", "10");
		map.put("roname", "admin");
		list.add(map);
		map = new HashMap<String, String> ();
		map.put("roid", "20");
		map.put("roname", "sale");
		list.add(map);
		map = new HashMap<String, String> ();
		map.put("roid", "30");
		map.put("roname", "market");
		list.add(map);
		return list;
	}
	
	@RequestMapping("/user/deps")
	public @ResponseBody List<Map<String,String>> getAllDeps(){
		List<Map<String, String>> list = new ArrayList<Map<String, String>> ();
		Map<String, String> map = new HashMap<String, String> ();
		map.put("depid", "1010");
		map.put("depname", "10TECHDP");
		list.add(map);
		map = new HashMap<String, String> ();
		map.put("depid", "1020");
		map.put("depname", "10SALEDP");
		list.add(map);
		map = new HashMap<String, String> ();
		map.put("depid", "1030");
		map.put("depname", "10MARKETDP");
		list.add(map);
		
		map = new HashMap<String, String> ();
		map.put("depid", "2010");
		map.put("depname", "20TECHDP");
		list.add(map);
		map = new HashMap<String, String> ();
		map.put("depid", "2020");
		map.put("depname", "20SALEDP");
		list.add(map);
		map = new HashMap<String, String> ();
		map.put("depid", "2030");
		map.put("depname", "30MARKETDP");
		list.add(map);
		
		return list;
	}
	
	@RequestMapping("/user/depsbyid")
	public @ResponseBody List<Map<String,String>> getAllDepsByid(HttpServletRequest req){
		String roid = req.getParameter("roid") == null ? "" : req.getParameter("roid");
		List<Map<String, String>> list = new ArrayList<Map<String, String>> ();
		if("10".equals(roid)) {
			Map<String, String> map = new HashMap<String, String> ();
			map.put("depid", "1010");
			map.put("depname", "10TECHDP");
			list.add(map);
			map = new HashMap<String, String> ();
			map.put("depid", "1020");
			map.put("depname", "10SALEDP");
			list.add(map);
			map = new HashMap<String, String> ();
			map.put("depid", "1030");
			map.put("depname", "10MARKETDP");
			list.add(map);
		} else if( "20".equals(roid) ){
			Map<String, String> map = new HashMap<String, String> ();
			map = new HashMap<String, String> ();
			map.put("depid", "2010");
			map.put("depname", "20TECHDP");
			list.add(map);
			map = new HashMap<String, String> ();
			map.put("depid", "2020");
			map.put("depname", "20SALEDP");
			list.add(map);
			map = new HashMap<String, String> ();
			map.put("depid", "2030");
			map.put("depname", "20MARKETDP");
			list.add(map);
		} else {
			Map<String, String> map = new HashMap<String, String> ();
			map = new HashMap<String, String> ();
			map.put("depid", "3010");
			map.put("depname", "30TECHDP");
			list.add(map);
			map = new HashMap<String, String> ();
			map.put("depid", "3020");
			map.put("depname", "30SALEDP");
			list.add(map);
			map = new HashMap<String, String> ();
			map.put("depid", "3030");
			map.put("depname", "30MARKETDP");
			list.add(map);
		}
		return list;
	}
}
