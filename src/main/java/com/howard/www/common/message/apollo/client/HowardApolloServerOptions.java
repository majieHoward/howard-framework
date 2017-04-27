package com.howard.www.common.message.apollo.client;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import net.sf.json.JSONObject;
/**
 * 
 * @ClassName:  HowardApolloServerOptions   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年4月21日 下午4:59:17   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class HowardApolloServerOptions {
	
	private MqttConnectOptions options = new MqttConnectOptions();
    /**
     * 
     * @Title: evaluateCleanSession   
     * @Description: TODO
     * 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
     * 这里设置为true表示每次连接到服务器都以新的身份连接 
     * @param: @param isCleanSession      
     * @return: void      
     * @throws
     */
	public void evaluateCleanSession(boolean isCleanSession) {
		//Boolean 默认值 flase
		options.setCleanSession(isCleanSession);
	}
	/**
	 * 
	 * @Title: evaluateUserName   
	 * @Description: TODO
	 * 设置连接的用户名
	 * @param: @param userName      
	 * @return: void      
	 * @throws
	 */
	public void evaluateUserName(String userName){
		options.setUserName(userName);
	}
	/**
	 * 
	 * @Title: evaluatePassword   
	 * @Description: TODO
	 * 设置连接的密码
	 * @param: @param password      
	 * @return: void      
	 * @throws
	 */
	public void evaluatePassword(String password){
		options.setPassword(password.toCharArray());
	}
	/**
	 * 
	 * @Title: evaluateConnectionTimeout   
	 * @Description: TODO
	 * 设置超时时间 单位为秒
	 * @param: @param timeOutValue      
	 * @return: void      
	 * @throws
	 */
	public void evaluateConnectionTimeout(int timeOutValue){
		if(timeOutValue==0){
			timeOutValue=10;
		}
		options.setConnectionTimeout(timeOutValue);
	}
	/**
	 * 
	 * @Title: evaluateKeepAliveInterval   
	 * @Description: TODO
	 * 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线
	 * 但这个方法并没有重连的机制   
	 * @param: @param keepAliveTimeValue      
	 * @return: void      
	 * @throws
	 */
	public void evaluateKeepAliveInterval(int keepAliveTimeValue){
		if(keepAliveTimeValue==0){
			keepAliveTimeValue=20;
		}
		options.setKeepAliveInterval(keepAliveTimeValue);
	}
	public MqttConnectOptions evaluateOptionsAttribute(JSONObject attributeSet){
		
		return obtainConnectOptions();
	}
	
	public MqttConnectOptions obtainConnectOptions(){
		return this.options;
	}
}
