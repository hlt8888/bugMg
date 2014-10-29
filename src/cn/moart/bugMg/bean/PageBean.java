package cn.moart.bugMg.bean;

public class PageBean {
	int page 	= 1;
	int rows 	= 1;
	int begin 	= 1;
	int end 	= 1;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin() {
		this.begin = (this.page-1)*this.rows+1;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd() {
		this.end = this.page*this.rows;
	}
}
