package com.howard.www.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.howard.www.business.dao.IBusinessInitparametersDao;
import com.howard.www.business.service.IBusinessInitparametersService;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Repository("businessInitparametersService")
public class BusinessInitparametersServiceImpl implements IBusinessInitparametersService {
	protected final Logger log = LoggerFactory
			.getLogger(BusinessInitparametersServiceImpl.class);
	@Autowired
	private ApplicationContext cApplicationContext;

	public JSONArray obtainInitparametersItems(IDataTransferObject queryParameters) throws Exception {
		List<JSONObject> paramItems=obtainIBusinessInitparametersDao().obtainInitparametersItems(queryParameters);
		for(JSONObject paramItem:paramItems){
			log.info(FrameworkStringUtils.asString(paramItem));
		}
		return null;
	}
    private IBusinessInitparametersDao obtainIBusinessInitparametersDao(){
    	return (IBusinessInitparametersDao) cApplicationContext.getBean("businessInitparametersDao");
    }
}
