package cn.moart.bugMg.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moart.bugMg.dao.LoginDao;

@Service
public class LoginService {
	@Autowired
	private LoginDao dao;
	
	public Map<String, Object> getMUserByEmailAndPwd(String email, String pwd) {
		return dao.getMUserByEmailAndPwd(email, pwd);
	}
}
