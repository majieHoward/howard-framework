package com.howard.www.core.hbatis.sql.executor.dao.impl;

import org.springframework.stereotype.Repository;
import net.sf.json.JSONArray;

import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.core.hbatis.sql.executor.dao.IQuerySqlStatementFormSourceFromDatabaseDao;

/**
 * 
 * @ClassName:  QuerySqlStatementFormSourceFromDatabaseDaoImpl   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:17:23   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Repository("querySqlStatementFormSourceFromDatabaseDao")
public class QuerySqlStatementFormSourceFromDatabaseDaoImpl extends BaseDaoImpl
		implements IQuerySqlStatementFormSourceFromDatabaseDao {

	public JSONArray obtainOriginalSqlStatementItems(
			IDataTransferObject queryParameters) throws Exception {
		return obtainQuery().evaluetePrimitiveSqlResource("select * from ${sqlTableName}")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

}
