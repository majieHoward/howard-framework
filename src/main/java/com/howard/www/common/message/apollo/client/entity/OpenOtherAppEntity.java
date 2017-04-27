package com.howard.www.common.message.apollo.client.entity;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OpenOtherAppEntity extends BaseMsgEntity {
	
	private String applicationPackageName;

	public OpenOtherAppEntity(IDataTransferObject queryParameters) {
		this.setMessageType("openOtherILogicalProcessing");
		this.setEquipment(FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("switchingInterfaceEquipment")));
		this.setApplicationPackageName(FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("packageNameChange")));
	}
}
