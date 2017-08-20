package com.howard.www.websocket.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 
 * @ClassName: WebSocketConfig
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: mayijie
 * @date: 2017年8月20日 下午9:32:49
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	/**
	 * 
	 * <p>
	 * Title: registerStompEndpoints
	 * </p>
	 * <p>
	 * Description: 添加一个服务端点，来接收客户端的连接 客户端在订阅或发布消息到目的地址前，要连接该端点,端点的
	 * 作用客户端在订阅或发布消息到目的地址前，要连接该端点
	 * </p>
	 * 
	 * @param registry
	 * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry)
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/**
		 * STOMP，Streaming Text Orientated Message Protocol，
		 * 是流文本定向消息协议，是一种为MOM(Message Oriented Middleware，
		 * 面向消息的中间件)设计的简单文本协议它提供了一个可互操作的连接格式，
		 * 允许STOMP客户端与任意STOMP消息代理(Broker)进行交互，
		 * 类似于OpenWire(一种二进制协议)。其中最流行的STOMP消息代理是 Apache ActiveMQ
		 */
		/**
		 * 表示添加了一个/hospitalQueue端点，客户端就可以通过这个端点来进行连接 
		 * withSockJS()的作用是开启SockJS支持
		 * setAllowedOrigins()方法表示允许连接的域名
		 */
		/**
		 * 配置允许的Origin头值.此检查主要是为浏览器客户端设计的。没有什么可以阻止其他类型的客户
		 * 端修改 Origin标头值.启用S​​ockJS并且起源受到限制时,不允许检查请求来源
		 * (基于JSONP和Iframe的传输)的传输类型被禁用.因此,当起源受到限制时,
		 * 不支持IE 6到9.每个提供的允许来源必须以
		 * "http://","https://"或"*"开头(意味着允许所有来源).
		 * 默认情况下,只允许同一个原始请求（空列表）
		 * 使用通配符 *,表示当前服务端通话任何域名发起请求
		 */
		registry.addEndpoint("/hospitalQueue").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/**
		 * 启用一个简单的消息代理并配置一个或多个前缀来过滤定位到代理的目标
		 * (例如，以"/topic"为前缀的目标)
		 */
		registry.enableSimpleBroker("/screenDevice", "/callTheName");
		/**
		 * 指服务端接收地址的前缀，意思就是说客户端给服务端发消息的地址的前缀
		 * 表示客户单向服务器端发送时的主题上面需要加"/app"作为前缀
		 */
		registry.setApplicationDestinationPrefixes("/app");
		/**
		 *配置用于标识用户目的地的前缀。
		 *用户目的地提供用户订阅其会话唯一的队列名称的能力，
		 *以及其他用户将消息发送到这些唯一的，用户特定的队列。
		 *例如，当用户尝试订阅"/user/queue/position-updates"时,
		 *目的地可以转换为"/queue/position-updatesi9oqdfzo",
		 *产生不与尝试执行的任何其他用户相冲突的唯一队列名称一样.
		 *随后当消息发送到"/user/{username}/queue/position-updates"时,
		 *目的地被转换为"/queue/position-updatesi9oqdfzo".
		 *用于标识这些目的地的默认前缀是"/user/"
		 */
		registry.setUserDestinationPrefix("/screenDevice/");
		
	}

}
