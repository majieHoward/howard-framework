package com.howard.www.common.message.apollo.client;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HowardQualityOfServiceEntity {
	private String describe;
	private String qosKey;
	private int qosValue;
	private String illustrate;
}
