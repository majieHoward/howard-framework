package com.howard.www.common.message.apollo.client.entity;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

public class ShutdownDeviceMsgEntity extends BaseMsgEntity {
	
	public ShutdownDeviceMsgEntity(IDataTransferObject queryParameters) {
		this.setMessageType("shutDownLogicalProcessing");
		this.setEquipment( FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("closeEquipment")));
		
	}
}
