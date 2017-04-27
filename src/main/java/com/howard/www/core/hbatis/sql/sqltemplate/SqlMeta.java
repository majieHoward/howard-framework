package com.howard.www.core.hbatis.sql.sqltemplate;

import java.util.List;

/**
 * 
 * @ClassName:  SqlMeta   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:24:58   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class SqlMeta {
	
	
	private String sql  ;
	
	private List<Object> parameter ;

	public SqlMeta(String sql, List<Object> parameter) {
		super();
		this.sql = sql;
		this.parameter = parameter;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getParameter() {
		return parameter;
	}

	public void setParameter(List<Object> parameter) {
		this.parameter = parameter;
	}

	@Override
	public String toString() {
		return "SqlInfo [sql=" + sql + ", parameter=" + parameter + "]";
	}
	
	

}
