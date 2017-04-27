package com.howard.www.business.domain;

import java.io.Serializable;

import com.howard.www.core.base.util.FrameworkStringUtils;

import net.sf.json.JSONObject;

public class LoginInfoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loginUserName;
	private String loginCount;
	private String userId;
	private String userNickName;
	
	
	public LoginInfoEntity(JSONObject loginUser) {
		this.setLoginUserName(FrameworkStringUtils.asString(loginUser.get("username")));
	    this.setUserId(FrameworkStringUtils.asString(loginUser.get("userId")));
	    this.setUserNickName(FrameworkStringUtils.asString(loginUser.get("nickname")));
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}
	
}
