package com.howard.www.common.message.apollo.client;

import java.util.Vector;

public abstract class HowardMqttServerClient {

	private HowardMqttServerAttribute mqttServerAttribute;

	public void evaluateMqttServerAttribute(HowardMqttServerAttribute mqttServerAttribute) {
		this.mqttServerAttribute = mqttServerAttribute;
	}

	public HowardMqttServerAttribute obtainMqttServerAttribute() {
		return this.mqttServerAttribute;
	}

	public Vector<String> obtainMessageTheme() {
		if (mqttServerAttribute != null) {
			return mqttServerAttribute.getMqttMessageTopicItems();
		}
		return null;
	}

	public abstract void sendSubjectMessage(String messageValue,String messageTopic) throws Exception;
}
