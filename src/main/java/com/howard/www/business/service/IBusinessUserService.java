package com.howard.www.business.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IBusinessUserService {
	public JSONObject obtainUserInfo(IDataTransferObject queryParameters) throws Exception;

	public JSONObject userExitSystem(IDataTransferObject queryParameters) throws Exception;

    public JSONObject obtainUserInfoByUserId(IDataTransferObject queryParameters)throws Exception;
    
    public JSONObject obtainSetDeviceSounds(IDataTransferObject queryParameters)throws Exception;
    
    public JSONObject obtainSetSilentInstall(IDataTransferObject queryParameters)throws Exception;

    public JSONObject obtainSetShutDownDevice(IDataTransferObject queryParameters)throws Exception;
    
    public JSONArray obtainEquipmentItems(IDataTransferObject queryParameters)throws Exception;
    
    public JSONObject obtainOpenOtherApp(IDataTransferObject queryParameters)throws Exception;
}
