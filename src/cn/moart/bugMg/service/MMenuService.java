package cn.moart.bugMg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moart.bugMg.bean.MMenu;
import cn.moart.bugMg.bean.PageBean;
import cn.moart.bugMg.bean.QueryBugBean;
import cn.moart.bugMg.dao.MMenuDao;

@Service
public class MMenuService {
	@Autowired
	private MMenuDao dao;
	
	/**
	 * 获取菜单列表
	 */
	public List<MMenu> getMenus() {
		return dao.getMenus();
	}
	
}
