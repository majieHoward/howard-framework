package com.howard.www.business.domain;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @ClassName:  ScreenDeviceEntity   
 * @Description:TODO(屏幕设备信息放入到redis或cache中,屏幕的IP地址作为Key,ScreenDeviceEntity作为Value)   
 * @author: mayijie
 * @date:   2017年8月21日 上午10:18:38   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenDeviceEntity implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	//eg.171.217.95.14
	private String internetProtocol;
	//eg.351521004992889
	private String screenDeviceIdentity;
	//eg.A1524
    private ScreenDeviceSpecificationEntity specification;
    //是否可用'10A' '10X'
  	private String available;
}
