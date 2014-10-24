package cn.moart.bugMg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moart.bugMg.dao.HelloDao;

@Service
public class HelloService {
	@Autowired
	HelloDao dao;
	
	public List<String> ppt(){
		return dao.getStringList();
	}
}
