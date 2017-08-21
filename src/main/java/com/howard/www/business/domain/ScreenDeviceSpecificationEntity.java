package com.howard.www.business.domain;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
/**
 * 
 * @ClassName:  ScreenDeviceTypeEntity   
 * @Description:TODO(屏幕类型)   
 * @author: mayijie
 * @date:   2017年8月21日 上午10:24:26   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenDeviceSpecificationEntity implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	//eg.屏幕规格编号A1524
	private String screenSerialEncoding;
	//eg.屏幕规格描述
	private String screenSerialDescribe;
	//eg.P5632
	private PageDisplayEntity pageDisplay;
	//是否可用'10A' '10X'
  	private String available;
}
