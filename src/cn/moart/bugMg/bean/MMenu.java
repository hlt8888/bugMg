package cn.moart.bugMg.bean;

import java.util.List;

public class MMenu {
	
	private String id;
	private String name;
	private String icon;
	private String url;
	private String parent_id = "0";
	private List<MMenu> menus;
	
	public List<MMenu> getMenus() {
		return menus;
	}
	public void setMenus(List<MMenu> menus) {
		this.menus = menus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
 
}