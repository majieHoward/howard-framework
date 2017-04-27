package com.howard.www.common.message.apollo.client;

import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.howard.www.core.base.util.FrameworkStringUtils;

/**
 * 
 * @ClassName: HowardApolloServerClient
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: mayijie
 * @date: 2017年4月21日 下午4:18:46
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class HowardApolloServerClient extends HowardMqttServerClient {

	private MqttClient apolloClient;

	private MqttConnectOptions options;

	private HowardApolloServerCallback apolloServerCallback;

	private ConcurrentHashMap<String, MqttTopic> apolloMessageTopicList = new ConcurrentHashMap<String, MqttTopic>();

	public void evaluateConnectOptions(MqttConnectOptions options) {
		this.options = options;
	}

	public void evaluateApolloServerCallback(HowardApolloServerCallback apolloServerCallback) {
		this.apolloServerCallback = apolloServerCallback;
	}

	private MqttTopic obtainMqttTopicByTopicName(String topicName) {
		if (!"".equals(FrameworkStringUtils.asString(topicName))) {
			if (apolloMessageTopicList.get(topicName) == null) {
				apolloMessageTopicList.put(topicName, apolloClient.getTopic(topicName));
			}
			return apolloMessageTopicList.get(topicName);
		}
		return null;
	}

	private void initializeMessageTheme() {
		if (obtainMessageTheme() != null && obtainMessageTheme().size() > 0) {
			for (String mqttMessageTopicItem : obtainMessageTheme()) {
				apolloMessageTopicList.put(mqttMessageTopicItem, apolloClient.getTopic(mqttMessageTopicItem));
			}
		}
	}

	public void evaluateMqtClient() {
		try {
			apolloClient = new MqttClient(obtainMqttServerAttribute().getMqttServerHost(),
					obtainMqttServerAttribute().getMqttClientIdValue(), new MemoryPersistence());
			apolloClient.setCallback(apolloServerCallback);
			apolloClient.connect(options);
			initializeMessageTheme();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendSubjectMessage(String messageValue, String messageTopic) throws Exception {
		// TODO Auto-generated method stub
		if (!"".equals(FrameworkStringUtils.asString(messageValue))
				&& !"".equals(FrameworkStringUtils.asString(messageTopic))) {
			if(obtainMqttTopicByTopicName(messageTopic)!=null){
				MqttMessage message = new MqttMessage();
				message.setQos(1);
				message.setRetained(true);
				message.setPayload(messageValue.getBytes());
				MqttDeliveryToken token = obtainMqttTopicByTopicName(messageTopic).publish(message);
				token.waitForCompletion();
				System.out.println(token.isComplete() + "========");
			}
		}

	}

}
