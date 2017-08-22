package com.howard.www.websocket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

@Controller
public class HospitalQueueWebsocketController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/messageForwarding.websocket")
	public void messageForwarding(IDataTransferObject requiredParameter){
		messagingTemplate.convertAndSend("/callTheName/public", "已经成功接收"); 
	}
}
