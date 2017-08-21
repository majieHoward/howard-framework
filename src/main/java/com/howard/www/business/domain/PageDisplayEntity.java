package com.howard.www.business.domain;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
/**
 * 
 * @ClassName:  PageDisplayEntity   
 * @Description:TODO(屏幕展示的页面模板详情)   
 * @author: mayijie
 * @date:   2017年8月21日 上午11:17:26   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageDisplayEntity implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	//eg.P5632
	private String pageIdentity;
	//eg.诊室外的叫号屏幕主页
	private String pageName;
	//eg.诊室外的叫号屏幕主页
	private String pageDescribe;
	//eg.http://171.217.95.100:9999/index.howard
	private String pageUrlAddress;
	//是否可用'10A' '10X'
  	private String available;
	
}
