package com.howard.www.core.hbatis.sql.executor.service;

import java.util.concurrent.ConcurrentHashMap;

import com.howard.www.core.hbatis.sql.mapping.MappedStatement;
/**
 * 
 * @ClassName:  IQuerySqlStatementFormSource   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:23:53   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public interface IQuerySqlStatementFormSource {
	public void queryDefinitionSqlStatement(String sqlTableName,
			ConcurrentHashMap<String, MappedStatement> sqlSource)
			throws Exception;

	public void queryDefinitionSqlStatement(String sqlTableName, String sqlId,
			String namespace,
			ConcurrentHashMap<String, MappedStatement> sqlSource)
			throws Exception;
	
	public void replaceDefinitionSqlStatement(String sqlTableName, String sqlId,
			String namespace,
			ConcurrentHashMap<String, MappedStatement> sqlSource)throws Exception;
}
