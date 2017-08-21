package com.howard.www.business.domain;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
/**
 * 
 * @ClassName:  ConsultingRoomEntity   
 * @Description:TODO(诊室详情信息放入到redis或cache中)   
 * @author: mayijie
 * @date:   2017年8月21日 上午11:16:51   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsultingRoomEntity implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	//eg.儿科1诊断室
	private String designation;
	//eg.1楼
	private String floorNumber;
	//eg.300
	private String roomCode;
	//eg.儿科1诊断室（1楼）
    private String roomName;
    //eg.11201
    private String deptCode;
    
    private String roomType;
    
    private String simpleName;
    //eg.EKZDSDLD
    private String inputCode;
    //eg.171.217.95.14 (诊室对应的屏幕信息)
    private ScreenDeviceEntity screenDevice;
    //eg.171.217.95.14(作为KEY)
  	private String internetProtocol;
  	//eg.351521004992889(作为KEY)
  	private String screenDeviceIdentity;
    //是否可用'10A' '10X'
  	private String available;
}
