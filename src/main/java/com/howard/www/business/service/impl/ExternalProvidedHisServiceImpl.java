package com.howard.www.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Repository;

import com.howard.www.business.service.IExternalProvidedHisService;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONObject;
@Repository("externalProvidedHisService")
public class ExternalProvidedHisServiceImpl implements IExternalProvidedHisService {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private ApplicationContext cApplicationContext;
	
	@Override
	public JSONObject callAPatientToSeeADoctor(IDataTransferObject paramDto) throws Exception {
		// TODO Auto-generated method stub
		messagingTemplate.convertAndSend("/callTheName/public", "已经成功接收"); 
		return null;
	}

}
