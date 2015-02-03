package cn.moart.bugMg.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.moart.bugMg.bean.PageBean;
import cn.moart.bugMg.bean.QueryBugBean;
import cn.moart.bugMg.util.BeanRefUtil;
import cn.moart.bugMg.util.DaoUtil;

@Repository
public class MUrlListDao {
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
//Add begin
	public void urllistAdd(Map<String, String> map) {
		String sql = "insert into m_middelurl2(url, artist_name) values "
				+ "(?,?)";
		Object[] parameters = new Object[] { map.get("url"), map.get("artist_name") };
		jdbcTemplate.update(sql, parameters);
	}
	
//Add end

//Update begin
	@SuppressWarnings("unchecked")
	public void updateUrlList(Map<String, String> map_set, Map<String, String> map_where){
		StringBuffer sql = new StringBuffer();
		sql.append("update m_middelurl2 ");
		
		Map<String, Object> map_sql_set = DaoUtil.getSqlUpdateSetByMap(map_set);
		Map<String, Object> map_sql_where = DaoUtil.getSqlWhereByMap(map_where);
		StringBuffer sql_set = (StringBuffer)map_sql_set.get("sql_set");
		StringBuffer sql_where = (StringBuffer)map_sql_where.get("sql_where");
		List<String> parms = new ArrayList<String>();
		parms.addAll((List<String>) map_sql_set.get("parms"));
		parms.addAll((List<String>) map_sql_where.get("parms"));
		
		sql.append(sql_set);
		sql.append(sql_where);
		
		jdbcTemplate.update(sql.toString(), parms.toArray());
	}
//Update end
	
//Search begin
	/**
	 * get all bugs
	 */
	public PageBean getAll(Map<String, String> query) {
		StringBuffer sql_total = new StringBuffer();
		sql_total.append("select count(id) as total ");
		String sql_fields = "select id, url,artist_name ";
		String sql_from = " from m_middelurl2 ";
		List<String> parms = new ArrayList<String> ();
		sql_total.append(sql_from);
        long total = DaoUtil.getTotal(sql_total, parms.toArray(), jdbcTemplate);
        
        PageBean page = new PageBean(total, Integer.valueOf(query.get("rows")));
        page.setCurrentPageNo(Integer.valueOf(query.get("page")));
        
        StringBuffer sql = new StringBuffer();
        sql.append(sql_fields);
        sql.append(sql_from);
        sql.append(" limit "+String.valueOf(page.getCurrentPageStartRecord())+","+String.valueOf(query.get("rows")));
        System.out.println(sql);
		List<Map<String,Object>> data = jdbcTemplate.queryForList(sql.toString(),parms.toArray());
		page.setRows(data);
		return page;
	}
	
//Search end
}
