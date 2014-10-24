package cn.moart.bugMg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxjsonUtil {

	@RequestMapping("/easyui/menu_data.json")
	public @ResponseBody
	String getMenus() {
		/*
		 * [ {"id":1,"parendId":0,"name":"Foods"},
		 * {"id":2,"parentId":1,"name":"Fruits"},
		 * {"id":3,"parentId":1,"name":"Vegetables"},
		 * {"id":4,"parentId":2,"name":"apple"},
		 * {"id":5,"parentId":2,"name":"orange"},
		 * {"id":6,"parentId":3,"name":"tomato"},
		 * {"id":7,"parentId":3,"name":"carrot"},
		 * {"id":8,"parentId":3,"name":"cabbage"},
		 * {"id":9,"parentId":3,"name":"potato"},
		 * {"id":10,"parentId":3,"name":"lettuce"} ]
		 */
		StringBuffer sb = new StringBuffer();
		sb.append("[ {\"id\":1,\"parendId\":0,\"name\":\"Foods\"},");
		sb.append("{\"id\":2,\"parentId\":1,\"name\":\"Fruits\"},");
		sb.append("{\"id\":3,\"parentId\":1,\"name\":\"Vegetables\"},");
		sb.append("{\"id\":4,\"parentId\":2,\"name\":\"<a href='http://www.baidu.com'>apple</a>\"},");
		sb.append("{\"id\":5,\"parentId\":2,\"name\":\"orange\"},");
		sb.append("{\"id\":6,\"parentId\":3,\"name\":\"tomato\"},");
		sb.append("{\"id\":7,\"parentId\":3,\"name\":\"carrot\"},");
		sb.append("{\"id\":8,\"parentId\":3,\"name\":\"cabbage\"},");
		sb.append("{\"id\":9,\"parentId\":3,\"name\":\"potato\"},");
		sb.append("{\"id\":10,\"parentId\":3,\"name\":\"lettuce\"} ]");
		List<Map<String, Object>> list_menu = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("parendId", 0);
		map.put("name", "Foods");
		list_menu.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 2);
		map.put("parendId", 1);
		map.put("name", "Fruits");
		list_menu.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 3);
		map.put("parendId", 1);
		map.put("name", "Vegetables");
		list_menu.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 4);
		map.put("parendId", 2);
		map.put("name", "apple");
		list_menu.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 5);
		map.put("parendId", 2);
		map.put("name", "orange");
		list_menu.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 6);
		map.put("parendId", 3);
		map.put("name", "tomato");
		list_menu.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 7);
		map.put("parendId", 3);
		map.put("name", "carrot");
		list_menu.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 8);
		map.put("parendId", 3);
		map.put("name", "cabbage");
		list_menu.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 9);
		map.put("parendId", 3);
		map.put("name", "potato");
		list_menu.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 10);
		map.put("parendId", 3);
		map.put("name", "lettuce");
		list_menu.add(map);

		return sb.toString();
	}
}
