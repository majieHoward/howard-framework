package com.howard.www.common.message.apollo.client;

import java.util.Vector;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HowardMqttServerAttribute {

	public HowardMqttServerAttribute() {
		// TODO Auto-generated constructor stub
	}

	private String mqttServerHost;

	private Vector<String> mqttMessageTopicItems = new Vector<String>();
	

	private String mqttClientIdValue;

	public void evaluateMqttMessageTopicItem(String mqttMessageTopicItem) {
		if (!"".equals(mqttMessageTopicItem)) {
			mqttMessageTopicItems.add(mqttMessageTopicItem);
		}
	}
	/**
	 * At Most Once (QoS=0) At Least Once (QoS=1) Exactly Once (QoS=2) 最多一次
	 * 这个设置时推送消息给Client，可靠性最低的一种。 如果设置Qos=0，那broker就不会返回结果码，告诉你他收到消息了，
	 * 也不会在失败后尝试重发。这有点像不可靠消息，如JMS。 至少一次 该设置会确保消息会被至少一次推送到Client。
	 * 如果推送设置为至少推送一次，Apollo会返回一个回调函数，确保代理已经收到消息， 而且确保会确保推送该消息。如果Client
	 * 将发布了一个Qos=1的消息， 如果在指定的时间内没有收到回复，Client会希望重新发布这个消息。
	 * 所以可能存在这种情况：代理收到一个需要推送的消息， 然后又收到一个消息推送到同一个Client。所以如果传输过程中PUBACK丢失，
	 * Client会重新发送，而且不会去检测是否是重发，broker就将消息发送到订阅主题中。 恰好一次
	 * 该设置是可靠等级最高的。他会确保发布者不仅仅会推送， 而且不会像Qos=1 那样，会被接收两次。当然这个设置会增加网络的负载。
	 * 当一个消息被发布出去的时候，broker会保存该消息的id,而且会利用任何长连接， 坚持要把该消息推送给目标地址。如果Client收到PUBREC
	 * 标志， 那就表明broker已经收到消息了。 这个时候broker会期待Client发送一个PUBREL 来清除session
	 * 中消息id，broker如果发送成功就会发送一个PUBCOMP通知Client。
	 */
	
}
