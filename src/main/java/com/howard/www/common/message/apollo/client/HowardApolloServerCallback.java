package com.howard.www.common.message.apollo.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
/**
 * 
 * @ClassName:  HowardApolloServerCallback   
 * @Description:TODO(这里用一句话描述这个类的作用)  
 *  
 * @author: mayijie
 * @date:   2017年4月21日 下午4:28:16   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class HowardApolloServerCallback implements MqttCallback {
	/**
	 * 
	 * <p>Title: connectionLost</p>   
	 * <p>Description: 当与服务器的连接丢失时，将调用此方法</p>   
	 * @param cause   
	 * @see org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.Throwable)
	 */
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		System.out.println("connectionLost-----------");
	}

    /**
     * 
     * <p>Title: deliveryComplete</p>   
     * <p>Description: 在邮件的传送完成后调用，并且已收到所有确认</p>   
     * @param token   
     * @see org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.paho.client.mqttv3.IMqttDeliveryToken)
     */
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		System.out.println("deliveryComplete---------" + token.isComplete());
	}

	/**
	 * 
	 * <p>Title: messageArrived</p>   
	 * <p>Description: 当消息从服务器到达时，将调用此方法</p>   
	 * @param topic
	 * @param message
	 * @throws Exception   
	 * @see org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.String, org.eclipse.paho.client.mqttv3.MqttMessage)
	 */
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("messageArrived----------");
	}

}
