package cn.moart.bugMg.bean;

public class QueryBugBean {
	Integer page;
	Integer rows;
	Integer action;
	
	public Integer getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
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
