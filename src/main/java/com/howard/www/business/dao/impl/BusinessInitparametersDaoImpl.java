package com.howard.www.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.howard.www.business.dao.IBusinessInitparametersDao;
import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;
@Repository("businessInitparametersDao")
public class BusinessInitparametersDaoImpl extends BaseDaoImpl implements IBusinessInitparametersDao {

	public JSONArray obtainInitparametersItems(IDataTransferObject queryParameters) throws Exception {
		
		return obtainQuery().evaluetePrimitiveSqlResource("select * from initparameters").evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

}
