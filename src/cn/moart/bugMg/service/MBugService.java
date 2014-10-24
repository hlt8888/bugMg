package cn.moart.bugMg.service;

import java.util.List;

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
	public List<MBug> getAll() {
		return dao.getAll();
	}
}
