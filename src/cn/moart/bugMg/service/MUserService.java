package cn.moart.bugMg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moart.bugMg.bean.MUser;
import cn.moart.bugMg.dao.MUserDao;

@Service
public class MUserService {
	@Autowired
	private MUserDao dao;
	
	/**
	 * 检索所有的User
	 */
	public List<MUser> getAll() {
		return dao.getAll();
	}
}
