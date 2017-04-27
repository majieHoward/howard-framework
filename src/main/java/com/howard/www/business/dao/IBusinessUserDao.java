package com.howard.www.business.dao;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IBusinessUserDao {
	public JSONArray obtainUserInfo(IDataTransferObject queryParameters) throws Exception;

	public JSONObject obtainUserInfoByUserId(IDataTransferObject queryParameters) throws Exception;
}
