package cn.moart.bugMg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.moart.bugMg.bean.MBug;

@Repository
public class MBugDao {
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 检索所有的User
	 */
	public List<MBug> getAll() {
		String sql = "select id, name, content from m_bug";

		// Maps a SQL result to a Java object
		RowMapper<MBug> mapper = new RowMapper<MBug>() {
			public MBug mapRow(ResultSet rs, int rowNum) throws SQLException {
				MBug bug = new MBug();
				bug.setId(rs.getInt("id"));
				bug.setName(rs.getString("name"));
				bug.setContent(rs.getString("content"));
				return bug;
			}
		};

		return jdbcTemplate.query(sql, mapper);
	}
}
