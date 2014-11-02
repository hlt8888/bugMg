package cn.moart.bugMg.bean;

public class QueryUserBean {
	Integer page;
	Integer rows;
	Integer name;
	
	public Integer getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
