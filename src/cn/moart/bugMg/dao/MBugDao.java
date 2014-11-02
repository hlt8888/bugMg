package cn.moart.bugMg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.moart.bugMg.bean.MBug;
import cn.moart.bugMg.bean.PageBean;
import cn.moart.bugMg.bean.QueryBugBean;
import cn.moart.bugMg.util.BeanRefUtil;
import cn.moart.bugMg.util.DaoUtil;

@Repository
public class MBugDao {
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
//Add begin
	public void bugAdd(MBug bug) {
		String sql = "insert into m_bug(name, content, createdby, action, repair) values "
				+ "(?, ?, ?, ?, ?)";
		Object[] parameters = new Object[] { bug.getName(), bug.getContent(), bug.getCreatedby(), bug.getAction(), bug.getRepair()};
		jdbcTemplate.update(sql, parameters);
	}
//Add end

//Update begin
	@SuppressWarnings("unchecked")
	public void updateBug(Map<String, String> map_set, Map<String, String> map_where){
		StringBuffer sql = new StringBuffer();
		sql.append("update m_bug ");
		
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
	public PageBean getAll(QueryBugBean query) {
		StringBuffer sql_total = new StringBuffer();
		sql_total.append("select count(id) as total ");
		String sql_fields = "select id, name, content ";
		String sql_from = " from m_bug ";
		StringBuffer sql_where = null;
		List<String> parms = null;
		Map<String, String> fieldValMap = BeanRefUtil  
                .getFieldValueMap(query);
		Map<String, Object> sqlInfo = DaoUtil.getSqlWhereByMap(fieldValMap);
		sql_where = (StringBuffer) sqlInfo.get("sql_where");
		parms = (List<String>) sqlInfo.get("parms");
		sql_total.append(sql_from);
        sql_total.append(sql_where);
        long total = DaoUtil.getTotal(sql_total, parms.toArray(), jdbcTemplate);
        
        PageBean page = new PageBean(total, query.getRows());
        page.setCurrentPageNo(query.getPage());
        
        StringBuffer sql = new StringBuffer();
        sql.append(sql_fields);
        sql.append(sql_from);
        sql.append(sql_where);
        sql.append(" limit "+String.valueOf(page.getCurrentPageStartRecord())+","+String.valueOf(page.getCurrentPageEndRecord()));
        
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
		List<Map<String,Object>> data = jdbcTemplate.queryForList(sql.toString(),parms.toArray());
		page.setRows(data);
		return page;
	}
	
	
	public Map<String, Object> getMBugById(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select m_bg.id, m_bg.name, m_bg.content, m_u.name as username,"
				+ "m_bg.createddate, m_bg.action, m_bg.createdby ");
		sql.append(" from m_bug m_bg");
		sql.append(" left join m_user m_u on m_bg.createdby = m_u.id ");
		sql.append(" where m_bg.id = ? order by createddate desc ");
		Object[] parameters = new Object[] { id };
		return jdbcTemplate.queryForMap(sql.toString(),parameters);
	}

	public List<Map<String,Object>> getMessagesByBugid(int bug_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select m_msg.id, m_msg.message, m_u.name as username,m_msg.createddate ");
		sql.append(" from m_message m_msg");
		sql.append(" left join m_user m_u on m_msg.createdby = m_u.id ");
		sql.append(" where m_bug_id = ? order by createddate desc ");
		// Maps a SQL result to a Java object
		RowMapper<Map<String, Object>> mapper = new RowMapper<Map<String, Object>>() {
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String,Object> bug = new HashMap<String,Object>();
				bug.put("id", rs.getInt("id"));
				bug.put("message", rs.getString("message"));
				bug.put("createddate", rs.getString("createddate"));
				bug.put("username", rs.getString("username"));
				return bug;
			}
		};
		return jdbcTemplate.query(sql.toString(), mapper, bug_id);
	}
//Search end
	
}
