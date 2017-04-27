package com.howard.www.business.domain;

import java.io.Serializable;

import com.howard.www.core.base.util.FrameworkStringUtils;

import net.sf.json.JSONObject;


public class BackInteractivenfoEntity implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	public String isSuccess;
    public String interactiveMessage;
    public String requestAddress;
    public Object interactiveData;
	public String getIsSuccess() {
		if(FrameworkStringUtils.isEmpty(isSuccess)){
			this.isSuccess="success";
		}
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getInteractiveMessage() {
		return interactiveMessage;
	}
	public void setInteractiveMessage(String interactiveMessage) {
		this.interactiveMessage = interactiveMessage;
	}
	public Object getInteractiveData() {
		if(interactiveData==null){
			this.interactiveData=new JSONObject();
		}
		return interactiveData;
	}
	public void setInteractiveData(Object interactiveData) {
		this.interactiveData = interactiveData;
	}
	public String getRequestAddress() {
		return requestAddress;
	}
	public void setRequestAddress(String requestAddress) {
		this.requestAddress = requestAddress;
	}
	
    
}
