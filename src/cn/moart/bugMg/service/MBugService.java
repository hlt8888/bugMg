package cn.moart.bugMg.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moart.bugMg.bean.MBug;
import cn.moart.bugMg.bean.PageBean;
import cn.moart.bugMg.dao.MBugDao;

@Service
public class MBugService {
	@Autowired
	private MBugDao dao;
	
	/**
	 * 检索所有的User
	 */
	public List<Map<String, Object>> getAll(PageBean page) {
		return dao.getAll(page);
	}
	
	public Map<String, Object> getMBugById(int id) {
		return dao.getMBugById(id);
	}

	public void bugAdd(MBug bug) {
		// TODO Auto-generated method stub
		dao.bugAdd(bug);
	}
}
