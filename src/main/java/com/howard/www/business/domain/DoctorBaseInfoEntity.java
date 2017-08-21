package com.howard.www.business.domain;

import java.io.Serializable;

import com.howard.www.business.domain.GoodsEntity.GoodsEntityBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @ClassName: DoctorBaseInfoEntity
 * @Description:TODO(医生的基础信息可以放入到redis中)
 * @author: mayijie
 * @date: 2017年8月21日 上午9:17:21
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DoctorBaseInfoEntity  implements Serializable {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	//医生工号
	private String doctorJobNumber;
	//医生简介
	private String doctorIntroduction;
	//医生姓名
	private String doctorName;
	//医生头像照片以base64位存储
	
	
}
