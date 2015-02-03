package cn.moart.bugMg.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
 
/**
 * Service for initializing Redis with sample data
 */
@Component("initService")
public class InitRedisService {
 
	@Autowired
	private RedisTemplate<String, String> template;
	
	public void init() {
		// Delete existing ones
 
		String key = "menuid_"+"1000100";
		template.opsForHash().delete(key, "id");
		template.opsForHash().delete(key, "name");
		template.opsForHash().delete(key, "icon");
		template.opsForHash().delete(key, "url");
		template.opsForHash().delete(key, "parent_id");
		
		key = "menuid_"+"1000101";
		template.opsForHash().delete(key, "id");
		template.opsForHash().delete(key, "name");
		template.opsForHash().delete(key, "icon");
		template.opsForHash().delete(key, "url");
		template.opsForHash().delete(key, "parent_id");
		
		key = "menuid_"+"1000102";
		template.opsForHash().delete(key, "id");
		template.opsForHash().delete(key, "name");
		template.opsForHash().delete(key, "icon");
		template.opsForHash().delete(key, "url");
		template.opsForHash().delete(key, "parent_id");
		
		key = "menuid_"+"1000103";
		template.opsForHash().delete(key, "id");
		template.opsForHash().delete(key, "name");
		template.opsForHash().delete(key, "icon");
		template.opsForHash().delete(key, "url");
		template.opsForHash().delete(key, "parent_id");
		
		key = "menuid_"+"1000200";
		template.opsForHash().delete(key, "id");
		template.opsForHash().delete(key, "name");
		template.opsForHash().delete(key, "icon");
		template.opsForHash().delete(key, "url");
		template.opsForHash().delete(key, "parent_id");
		
		key = "menuid_"+"1000201";
		template.opsForHash().delete(key, "id");
		template.opsForHash().delete(key, "name");
		template.opsForHash().delete(key, "icon");
		template.opsForHash().delete(key, "url");
		template.opsForHash().delete(key, "parent_id");
		
		key = "menuid_"+"1000202";
		template.opsForHash().delete(key, "id");
		template.opsForHash().delete(key, "name");
		template.opsForHash().delete(key, "icon");
		template.opsForHash().delete(key, "url");
		template.opsForHash().delete(key, "parent_id");
		
		// Create new records
		key = "menuid_"+"1000100";
		template.opsForHash().put(key, "id", "1000100");
		template.opsForHash().put(key, "name", "Bug管理");
		template.opsForHash().put(key, "icon", "icon-sys");
		template.opsForHash().put(key, "url", "");
		template.opsForHash().put(key, "parent_id", "0");
		template.opsForSet().add("menuid", key);

		key = "menuid_"+"1000101";
		template.opsForHash().put(key, "id", "1000101");
		template.opsForHash().put(key, "name", "未修复Bug");
		template.opsForHash().put(key, "icon", "icon-log");
		template.opsForHash().put(key, "url", "/views/bug/buglist.jsp?action=1");
		template.opsForHash().put(key, "parent_id", "1000100");
		template.opsForSet().add("menuid", key);

		key = "menuid_"+"1000102";
		template.opsForHash().put(key, "id", "1000102");
		template.opsForHash().put(key, "name", "修复中Bug");
		template.opsForHash().put(key, "icon", "icon-log");
		template.opsForHash().put(key, "url", "/views/bug/buglist.jsp?action=2");
		template.opsForHash().put(key, "parent_id", "1000100");
		template.opsForSet().add("menuid", key);

		key = "menuid_"+"1000103";
		template.opsForHash().put(key, "id", "1000103");
		template.opsForHash().put(key, "name", "已修复Bug");
		template.opsForHash().put(key, "icon", "icon-log");
		template.opsForHash().put(key, "url", "/views/bug/buglist.jsp?action=3");
		template.opsForHash().put(key, "parent_id", "1000100");
		template.opsForSet().add("menuid", key);
		
		key = "menuid_"+"1000200";
		template.opsForHash().put(key, "id", "1000200");
		template.opsForHash().put(key, "name", "项目成员列表");
		template.opsForHash().put(key, "icon", "icon-sys");
		template.opsForHash().put(key, "url", "");
		template.opsForHash().put(key, "parent_id", "0");
		template.opsForSet().add("menuid", key);

		key = "menuid_"+"1000201";
		template.opsForHash().put(key, "id", "1000201");
		template.opsForHash().put(key, "name", "正式员工");
		template.opsForHash().put(key, "icon", "icon-sys");
		template.opsForHash().put(key, "url", "/views/user/userlist.jsp?cat=1");
		template.opsForHash().put(key, "parent_id", "1000200");
		template.opsForSet().add("menuid", key);

		key = "menuid_"+"1000202";
		template.opsForHash().put(key, "id", "1000202");
		template.opsForHash().put(key, "name", "实习生");
		template.opsForHash().put(key, "icon", "icon-sys");
		template.opsForHash().put(key, "url", "/views/user/userlist.jsp?cat=2");
		template.opsForHash().put(key, "parent_id", "1000200");
		template.opsForSet().add("menuid", key);
		
		key = "menuid_"+"1000300";
		template.opsForHash().put(key, "id", "1000300");
		template.opsForHash().put(key, "name", "WiKi图片列表");
		template.opsForHash().put(key, "icon", "icon-sys");
		template.opsForHash().put(key, "url", "");
		template.opsForHash().put(key, "parent_id", "0");
		template.opsForSet().add("menuid", key);
		
		key = "menuid_"+"1000301";
		template.opsForHash().put(key, "id", "1000301");
		template.opsForHash().put(key, "name", "查询列表");
		template.opsForHash().put(key, "icon", "icon-sys");
		template.opsForHash().put(key, "url", "/views/imageabout/listurllist.jsp");
		template.opsForHash().put(key, "parent_id", "1000300");
		template.opsForSet().add("menuid", key);
	}
	
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		InitRedisService initService = (InitRedisService) app.getBean("initService");
		initService.init();
		System.out.println("bbb");
	}
}
