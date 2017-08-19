package com.howard.www.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.howard.www.common.message.apollo.client.HowardApolloServerCallback;
import com.howard.www.common.message.apollo.client.HowardApolloServerClient;
import com.howard.www.common.message.apollo.client.HowardApolloServerOptions;
import com.howard.www.common.message.apollo.client.HowardMqttServerAttribute;

/**@Configuration**/
public class SystemApolloServerConfig {

	//@Bean(name = "howardApolloServerCallback")
	public HowardApolloServerCallback initHowardApolloServerCallback() {
		return new HowardApolloServerCallback();
	}

	//@Bean(name = "howardMqttServerAttribute")
	//@Scope("prototype")
	public HowardMqttServerAttribute initHowardMqttServerAttribute() {
		HowardMqttServerAttribute howardMqttServerAttribute = new HowardMqttServerAttribute();
		howardMqttServerAttribute.setMqttServerHost("tcp://192.168.1.4:61613");
		//howardMqttServerAttribute.setMqttServerHost("tcp://192.168.253.1:61613");
		howardMqttServerAttribute.setMqttClientIdValue("advertisement");
		howardMqttServerAttribute.evaluateMqttMessageTopicItem("test/topic");
		return howardMqttServerAttribute;
	}

	//@Bean(name = "howardApolloServerOptions")
	//@Scope("prototype")
	public HowardApolloServerOptions initHowardApolloServerOptions() {
		HowardApolloServerOptions howardApolloServerOptions = new HowardApolloServerOptions();
		howardApolloServerOptions.evaluateCleanSession(false);
		howardApolloServerOptions.evaluateUserName("admin");
		howardApolloServerOptions.evaluatePassword("password");
		howardApolloServerOptions.evaluateKeepAliveInterval(20);
		howardApolloServerOptions.evaluateConnectionTimeout(10);
		return howardApolloServerOptions;
	}

	//@Bean(name = "howardApolloServerClient")
	public HowardApolloServerClient initHowardApolloServerClient(
			@Qualifier("howardMqttServerAttribute") HowardMqttServerAttribute howardMqttServerAttribute,
			@Qualifier("howardApolloServerCallback") HowardApolloServerCallback howardApolloServerCallback,
			@Qualifier("howardApolloServerOptions") HowardApolloServerOptions howardApolloServerOptions) {
		HowardApolloServerClient howardApolloServerClient = new HowardApolloServerClient();
		howardApolloServerClient.evaluateApolloServerCallback(howardApolloServerCallback);
		howardApolloServerClient.evaluateConnectOptions(howardApolloServerOptions.obtainConnectOptions());
		howardApolloServerClient.evaluateMqttServerAttribute(howardMqttServerAttribute);
		howardApolloServerClient.evaluateMqtClient();
		return howardApolloServerClient;
	}
}
