package cn.moart.bugMg.dao;

import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Map<String, Object> getMUserByEmailAndPwd(String email, String pwd) {
		String sql = "select id, name, email from m_user where email = ? and pwd = ? ";
		Object[] parameters = new Object[] { email, pwd };
		Map<String, Object> map = null;
		try {
			map = jdbcTemplate.queryForMap(sql,parameters);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
}
