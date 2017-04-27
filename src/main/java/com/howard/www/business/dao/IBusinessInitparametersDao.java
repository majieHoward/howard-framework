package com.howard.www.business.dao;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;

public interface IBusinessInitparametersDao {
	public JSONArray obtainInitparametersItems(IDataTransferObject queryParameters) throws Exception;

}
