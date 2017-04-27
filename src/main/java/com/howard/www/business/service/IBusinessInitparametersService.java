package com.howard.www.business.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;

public interface IBusinessInitparametersService {
	public JSONArray obtainInitparametersItems(IDataTransferObject queryParameters) throws Exception;
}
