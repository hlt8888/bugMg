package cn.moart.bugMg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.jdbc.core.JdbcTemplate;

public class DaoUtil {
	public static Map<String, Object> getSqlWhereByMap(Map<String, String> fieldValMap){
		StringBuffer sql_where = new StringBuffer();
		List<String> parms = new ArrayList<String>();
		Map<String, Object> result = new HashMap<String, Object>();
		int i = 0;
        for (Entry<String, String> entry : fieldValMap.entrySet()) {
        	String field = entry.getKey();
        	String val = entry.getValue();
        	i++;
    		if(i==1){
    			sql_where.append(" where ");
    		}
    		sql_where.append(" "+field+"=?");
    		if(fieldValMap.entrySet().size()-1 < i){
    			sql_where.append("and");
    		}
    		parms.add(val);
        } 
        result.put("sql_where", sql_where);
        result.put("parms", parms);
        return result;
	}
	
	public static Map<String, Object> getSqlUpdateSetByMap(Map<String, String> map_set){
		StringBuffer sql_where = new StringBuffer();
		List<String> parms = new ArrayList<String>();
		Map<String, Object> result = new HashMap<String, Object>();
		int i = 0;
        for (Entry<String, String> entry : map_set.entrySet()) {
        	String field = entry.getKey();
        	String val = entry.getValue();
        	i++;
    		if(i==1){
    			sql_where.append(" set ");
    		}
    		sql_where.append(" "+field+"=?");
    		if(map_set.entrySet().size() > i){
    			sql_where.append(",");
    		}
    		parms.add(val);
        } 
        result.put("sql_set", sql_where);
        result.put("parms", parms);
        return result;
	}
	
	public static long getTotal(StringBuffer sql, Object[] parms, JdbcTemplate jdbcTemplate){
		Map<String, Object> total = jdbcTemplate.queryForMap(sql.toString(), parms);
		return (Long) total.get("total");
	}
}
