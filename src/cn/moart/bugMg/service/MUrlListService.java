package cn.moart.bugMg.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moart.bugMg.bean.PageBean;
import cn.moart.bugMg.bean.QueryBugBean;
import cn.moart.bugMg.dao.MUrlListDao;

@Service
public class MUrlListService {
	@Autowired
	private MUrlListDao dao;
	
	/**
	 * 检索所有的Bug
	 */
	public PageBean getAll(Map<String, String> query) {
		return dao.getAll(query);
	}
	
	public void urllistAdd(Map<String, String> map) {
		// TODO Auto-generated method stub
		dao.urllistAdd(map);
	}
	
	public void updateUrlList(Map<String, String> map_set, Map<String, String> map_where){
		dao.updateUrlList(map_set, map_where);
	}

}
