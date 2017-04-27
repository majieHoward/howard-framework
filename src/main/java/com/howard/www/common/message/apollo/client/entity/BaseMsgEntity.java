package com.howard.www.common.message.apollo.client.entity;

import com.howard.www.core.base.util.FrameworkStringUtils;

import lombok.Getter;
import lombok.Setter;

public class BaseMsgEntity {
	@Setter
	@Getter
	public String equipment;
	@Setter
	@Getter
	public String messageType;

	@Setter
	public String timeStamp;

	public String getTimeStamp() {
		setTimeStamp(FrameworkStringUtils.asString(System.currentTimeMillis()));
		return timeStamp;
	}

}
