package cn.moart.bugMg.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.bean.PageBean;
import cn.moart.bugMg.bean.QueryBugBean;
import cn.moart.bugMg.service.MUrlListService;

@Controller
public class MUrlListController {
	@Autowired
	private MUrlListService service;
	
	@RequestMapping("/views/imageabout/urls")
	public @ResponseBody
	PageBean getAllUrlList(HttpServletRequest req, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
		Map<String, String> query = new HashMap<String, String> ();
		String rows = req.getParameter("rows") == null ? "10" : req.getParameter("rows");
		String _page	= req.getParameter("page") == null ? "1" : req.getParameter("page");
		
		if ("".equals(rows)) {
			rows = "10";
		}
		
		if ("".equals(_page)) {
			_page = "1";
		}
		
		query.put("rows", rows);
		query.put("page", _page);
		
		PageBean page = null;
		if(user != null){
			page = service.getAll(query);
		}
		return page;
	}
	
	@RequestMapping("/views/imageabout/saveurl")
	public @ResponseBody Map<String, String> urllistAdd(HttpServletRequest req, HttpSession session){
		String url = req.getParameter("url") == null ? "" : req.getParameter("url");
		String artist_name	= req.getParameter("artist_name") == null ? "" : req.getParameter("artist_name");
		
		Map<String, String> urllist = new HashMap<String, String> ();
		urllist.put("url", url);
		urllist.put("artist_name", artist_name);
		service.urllistAdd(urllist);
		return urllist;
	}
	
	@RequestMapping("/views/imageabout/updateurl")
	public @ResponseBody String updateBug(HttpServletRequest req, HttpSession session){
		String id = req.getParameter("id") == null ? "0" : req.getParameter("id");
		String url = req.getParameter("url") == null ? "" : req.getParameter("url");
		String artist_name	= req.getParameter("artist_name") == null ? "" : req.getParameter("artist_name");
		
		Map<String, String> map_set 	= new HashMap<String, String> ();
		Map<String, String> map_where 	= new HashMap<String, String> ();
		
		map_set.put("url", String.valueOf(url));
		map_set.put("artist_name", artist_name);
		map_where.put("id", String.valueOf(id));
		service.updateUrlList(map_set, map_where);
		return "OK";
	}
}
