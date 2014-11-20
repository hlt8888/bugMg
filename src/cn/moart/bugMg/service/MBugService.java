package cn.moart.bugMg.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moart.bugMg.bean.MBug;
import cn.moart.bugMg.bean.PageBean;
import cn.moart.bugMg.bean.QueryBugBean;
import cn.moart.bugMg.dao.MBugDao;

@Service
public class MBugService {
	@Autowired
	private MBugDao dao;
	
	/**
	 * 检索所有的Bug
	 */
	public PageBean getAll(QueryBugBean query) {
		return dao.getAll(query);
	}
	
	public Map<String, Object> getMBugById(int id) {
		return dao.getMBugById(id);
	}

	public void bugAdd(MBug bug) {
		// TODO Auto-generated method stub
		dao.bugAdd(bug);
	}
	
	public List<Map<String,Object>> getMessagesByBugid(int bug_id) {
		return dao.getMessagesByBugid(bug_id);
	}
	
	public void updateBug(Map<String, String> map_set, Map<String, String> map_where){
		dao.updateBug(map_set, map_where);
	}
}
