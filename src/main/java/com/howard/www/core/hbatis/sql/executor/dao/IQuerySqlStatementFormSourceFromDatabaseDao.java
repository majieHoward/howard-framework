package com.howard.www.core.hbatis.sql.executor.dao;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;

public interface IQuerySqlStatementFormSourceFromDatabaseDao {
	public JSONArray obtainOriginalSqlStatementItems(
			IDataTransferObject queryParameters) throws Exception;
}
