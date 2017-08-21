package com.howard.www.business.domain;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsultingRoomEntity implements Serializable{/**   
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
    
}
