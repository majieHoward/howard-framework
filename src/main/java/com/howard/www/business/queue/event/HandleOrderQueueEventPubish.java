package com.howard.www.business.queue.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName:  HandleOrderQueueEventPubish   
 * @Description:TODO 发布一个队列操作事件  
 * @author: mayijie
 * @date:   2017年2月17日 下午10:58:39   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Repository("handleOrderQueueEventPubish")
public class HandleOrderQueueEventPubish implements ApplicationContextAware {
	protected final Logger log = LoggerFactory.getLogger(HandleOrderQueueEventPubish.class);
    @Autowired
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void publishHandleOrderQueueEvent(ApplicationEvent event) {
		log.info("发布一个处理订单队列的事件");
		/**
		 * ApplicationContext自动到本地容器里找一个名字为"ApplicationEventMulticaster"的实现
		 * 如果没有自己new一个SimpleApplicationEventMulticaster
		 */
		/**
		 * ApplicationContext接口继承了ApplicationEventPublisher，并在AbstractApplicationContext实现了具体代码
		 * 实际执行是委托给ApplicationEventMulticaster
		 */
		applicationContext.publishEvent(event);
	}

}
