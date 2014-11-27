package cn.moart.bugMg.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import cn.moart.bugMg.bean.MMenu;

@Repository
public class MMenuDao {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public List<MMenu> getMenus() {
		List<MMenu> menus = new ArrayList<MMenu>();
		
		Collection<String> fieldKeys = new HashSet<String>();
		fieldKeys.add("id");
		fieldKeys.add("name");
		fieldKeys.add("icon");
		fieldKeys.add("url");
		fieldKeys.add("parent_id");
 
		Collection<String> keys = redisTemplate.opsForSet().members("menuid");
		for (String key: keys) {
			MMenu menu = new MMenu();
			menu.setId((String) redisTemplate.opsForHash().get(key, "id"));
			menu.setName((String) redisTemplate.opsForHash().get(key, "name"));
			menu.setIcon((String) redisTemplate.opsForHash().get(key, "icon"));
			menu.setUrl((String) redisTemplate.opsForHash().get(key, "url"));
			menu.setParent_id((String) redisTemplate.opsForHash().get(key, "parent_id"));
			
			menus.add(menu);
		}
		
		
		return menus;
	}
	
}
