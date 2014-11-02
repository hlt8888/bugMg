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

import cn.moart.bugMg.bean.MUser;

@Repository
public class MUserDao {
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 检索所有的User
	 */
	public List<MUser> getAll() {
		String sql = "select id, name, email from m_user";

		// Maps a SQL result to a Java object
		RowMapper<MUser> mapper = new RowMapper<MUser>() {
			public MUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				MUser user = new MUser();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				return user;
			}
		};

		return jdbcTemplate.query(sql, mapper);
	}

	/**
	 * 新增person
	 */
	public void add(String firstName, String lastName, Double money) {

		String sql = "insert into person(first_name, last_name, money) values "
				+ "(:firstName, :lastName, :money)";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstName", firstName);
		parameters.put("lastName", lastName);
		parameters.put("money", money);

		// Save
		jdbcTemplate.update(sql, parameters);

	}

	/**
	 * 删除指定Person
	 */
	public void delete(Integer id) {

		String sql = "delete from person where id = ?";

		Object[] parameters = new Object[] { id };

		jdbcTemplate.update(sql, parameters);
	}

	/**
	 * Edit指定的Person
	 */
	public void edit(Integer id, String firstName, String lastName, Double money) {

		String sql = "update person set first_name = :firstName, "
				+ "last_name = :lastName, money = :money where id = :id";

		// Assign values to parameters
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		parameters.put("firstName", firstName);
		parameters.put("lastName", lastName);
		parameters.put("money", money);

		// Edit
		jdbcTemplate.update(sql, parameters);

	}

	public List<MUser> autoComUserInfo(int maxRows, String name_startsWith) {
//		String sql_fields = "select id, name, content ";
//		String sql_from = " from m_user ";
//		StringBuffer sql_where = null;
        
        StringBuffer sql = new StringBuffer();
        sql.append("select id, name,email ");
        sql.append(" from m_user ");
        sql.append("where name like '%"+name_startsWith+"%' ");
        sql.append(" limit 0,"+maxRows);
        
        // Maps a SQL result to a Java object
 		RowMapper<MUser> mapper = new RowMapper<MUser>() {
 			public MUser mapRow(ResultSet rs, int rowNum) throws SQLException {
 				MUser user = new MUser();
 				user.setId(rs.getInt("id"));
 				user.setName(rs.getString("name"));
 				user.setEmail(rs.getString("email"));
 				return user;
 			}
 		};
 		System.out.println(sql);
		Object[] params = {};
		return jdbcTemplate.query(sql.toString(),mapper, params);
	}

}
