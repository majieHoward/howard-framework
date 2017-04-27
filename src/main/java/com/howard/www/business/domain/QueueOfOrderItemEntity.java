package com.howard.www.business.domain;

import java.io.Serializable;

import com.howard.www.core.base.util.FrameworkStringUtils;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: QueueOfOrderItemEntity
 * @Description:TODO
 * @author: mayijie
 * @date: 2017年2月16日 上午10:24:29
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class QueueOfOrderItemEntity implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	private String orderItemId;
	private String customerName;
	private String customerId;
	private String orderGenerationTime;
	private String orderItemState;
	
	public String getOrderItemState() {
		if(FrameworkStringUtils.isEmpty(this.orderItemState)){
			return "original";
		}
		return orderItemState;
	}

	public void setOrderItemState(String orderItemState) {
		this.orderItemState = orderItemState;
	}

	public QueueOfOrderItemEntity() {
		
	}

	public QueueOfOrderItemEntity(JSONObject orderItemData) {
		if(orderItemData!=null){
			this.setCustomerId(FrameworkStringUtils.asString(orderItemData.get("customerName")));
		    this.setCustomerName(FrameworkStringUtils.asString(orderItemData.get("customerName")));
		    this.setOrderGenerationTime(FrameworkStringUtils.asString(orderItemData.get("orderGenerationTime")));
		    this.setOrderItemId(FrameworkStringUtils.asString(orderItemData.get("orderId")));
		}
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderGenerationTime() {
		return orderGenerationTime;
	}

	public void setOrderGenerationTime(String orderGenerationTime) {
		this.orderGenerationTime = orderGenerationTime;
	}

}
