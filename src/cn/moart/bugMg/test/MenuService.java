package cn.moart.bugMg.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
 
@Service
public class MenuService {
	
	@Autowired
	private RedisTemplate<String, String> template;
 
	public Menu create(Menu menu) {
		String key = "menu"+menu.getId();
//		template.opsForHash().put(key, "id", menu.getId());
//		template.opsForHash().put(key, "firstName", menu.getFirstName());
//		template.opsForHash().put(key, "lastName", menu.getLastName());
//		template.opsForHash().put(key, "username", menu.getUsername());
//		template.opsForHash().put(key, "password", menu.getPassword());
//		template.opsForHash().put(key, "role", menu.getRole().getRole().toString());
		
		template.opsForSet().add("menu", key);
		return menu;
	}
 
	public Menu read(Menu menu) {
		return menu;
	}
 
	public List<Menu> readAll() {
		List<Menu> users = new ArrayList<Menu>();
		
		Collection<String> fieldKeys = new HashSet<String>();
		fieldKeys.add("id");
		fieldKeys.add("firstName");
		fieldKeys.add("lastName");
		fieldKeys.add("username");
		fieldKeys.add("password");
		fieldKeys.add("role");
 
		Collection<String> keys = template.opsForSet().members("menu");
		for (String key: keys) {
			Menu menu = new Menu();
			menu.setId((String) template.opsForHash().get(key, "id"));
//			menu.setFirstName((String) template.opsForHash().get(key, "firstName"));
//			menu.setLastName((String) template.opsForHash().get(key, "lastName"));
//			menu.setPassword((String) template.opsForHash().get(key, "password"));
//			menu.setUsername((String) template.opsForHash().get(key, "username"));
//			
//			Role role = new Role();
//			role.setRole(Integer.valueOf((String) template.opsForHash().get(key, "role")));
//			menu.setRole(role);
			
			users.add(menu);
		}
		
		
		return users;
	}
 
	public Menu update(Menu menu) {
//		String key = "menu"+menu.getUsername();
//		String existingRecord = (String) template.opsForHash().get(key, "id");
 
//		if (existingRecord == null) {
//			return null;
//		}
 
//		template.opsForHash().put(key, "firstName", menu.getFirstName());
//		template.opsForHash().put(key, "lastName", menu.getLastName());
//		template.opsForHash().put(key, "role", menu.getRole().getRole().toString());
// 
		return menu;
	}
 
//	public Boolean delete(Menu menu) {
//		String key = "menu"+menu.getUsername();
//		template.opsForHash().delete(key, "id");
//		template.opsForHash().delete(key, "firstName");
//		template.opsForHash().delete(key, "lastName");
//		template.opsForHash().delete(key, "username");
//		template.opsForHash().delete(key, "password");
//		template.opsForHash().delete(key, "role");
// 
//		String existingRecord = (String) template.opsForHash().get(key, "id");
//		Boolean existingMember = template.opsForSet().remove("menu", key);
//		
//		if (existingRecord != null) {
//			return false;
//		}
//		
//		if (existingMember == false) {
//			return false;
//		}
//		
//		return true;
//	}
}