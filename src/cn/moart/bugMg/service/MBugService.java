package cn.moart.bugMg.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moart.bugMg.bean.MBug;
import cn.moart.bugMg.dao.MBugDao;

@Service
public class MBugService {
	@Autowired
	private MBugDao dao;
	
	/**
	 * 检索所有的User
	 */
	public List<Map<String, Object>> getAll() {
		return dao.getAll();
	}
	
	public Map<String, Object> getMBugById(int id) {
		return dao.getMBugById(id);
	}

	public void bugAdd(String name, String content, int userid) {
		// TODO Auto-generated method stub
		dao.bugAdd(name, content, userid);
	}
}
