package com.howard.www.business.domain;


import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import net.sf.json.JSONObject;

@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)

public class EquipmentRichselectEntity implements Serializable{
	
	public EquipmentRichselectEntity(JSONObject EquipmentItem){
		
	}
	
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	private String id;
	private String value;
	
	
}
