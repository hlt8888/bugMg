package cn.moart.bugMg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.moart.bugMg.bean.MBug;
import cn.moart.bugMg.bean.PageBean;

@Repository
public class MBugDao {
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * get all bugs
	 */
	public List<Map<String,Object>> getAll(PageBean page) {
		String sql = "select id, name, content from m_bug limit ?,?";

		// Maps a SQL result to a Java object
		RowMapper<Map<String, Object>> mapper = new RowMapper<Map<String, Object>>() {
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String,Object> bug = new HashMap<String,Object>();
				bug.put("id", rs.getInt("id"));
				bug.put("name", rs.getString("name"));
				bug.put("content", rs.getString("content"));
				return bug;
			}
		};
		return jdbcTemplate.query(sql, mapper, page.getBegin(), page.getEnd());
	}
	
	
	public Map<String, Object> getMBugById(int id) {
		String sql = "select id, name, content from m_bug where id=?";
		Object[] parameters = new Object[] { id };
		return jdbcTemplate.queryForMap(sql,parameters);
	}

	public void bugAdd(MBug bug) {
		String sql = "insert into m_bug(name, content, createdby, action, repair) values "
				+ "(?, ?, ?, ?, ?)";
		Object[] parameters = new Object[] { bug.getName(), bug.getContent(), bug.getCreatedby(), bug.getAction(), bug.getRepair()};
		jdbcTemplate.update(sql, parameters);
	}
	
	public List<Map<String,Object>> getMessagesByBugid(int bug_id) {
		String sql = "select id, name, content from m_messge where m_bug_id = " + bug_id;

		// Maps a SQL result to a Java object
		RowMapper<Map<String, Object>> mapper = new RowMapper<Map<String, Object>>() {
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String,Object> bug = new HashMap<String,Object>();
				bug.put("id", rs.getInt("id"));
				bug.put("name", rs.getString("name"));
				bug.put("content", rs.getString("content"));
				return bug;
			}
		};
		return jdbcTemplate.query(sql, mapper);
	}
	
	public int getAllTotal(){
		
		return 0;
	}
}
