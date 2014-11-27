package cn.moart.bugMg.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.moart.bugMg.bean.MMenu;
import cn.moart.bugMg.service.MMenuService;

@Controller
public class MMenuController {
	@Autowired
	private MMenuService menuService;
	
	@RequestMapping("/menus")
	public @ResponseBody
	Map<String, List<MMenu>> getMenus(HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
		List<MMenu> list = null;
		Map<String, List<MMenu>> re_map = null;
		if(user != null){
			list = menuService.getMenus();
			
			List<MMenu> top_menus 		= new ArrayList<MMenu> ();
			
			List<MMenu> children_menus 	= new LinkedList<MMenu> ();
			
			for( MMenu menu: list ) {
				if( menu.getParent_id() == null || "0".equals(menu.getParent_id()) ) {
					top_menus.add(menu);
				} else {
					children_menus.add(menu);
				}
			}
			
			for( MMenu menu: top_menus ) {
				List<MMenu> _list = new LinkedList<MMenu> ();
				for (MMenu cld_menu: children_menus ) {
					if( cld_menu.getParent_id().equals(menu.getId()) ) {
						_list.add(cld_menu);
					}
				}
				menusSort(_list);
				menu.setMenus(_list);
			}
			menusSort(top_menus);
			re_map = new HashMap<String, List<MMenu>> ();
			re_map.put("menus", top_menus);
		}
		return re_map;
	}
	
	//Sort menus
	private List<MMenu> menusSort(List<MMenu> list) {
		Collections.sort(list,new Comparator<MMenu>(){
			@Override
			public int compare(MMenu m1, MMenu m2) {
				return m1.getId().compareTo(m2.getId());
			}
        });
		
		return list;
	}
}
