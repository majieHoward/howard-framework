package com.howard.www.business.queue.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import com.howard.www.business.queue.service.IHandleOrderQueueEvent;
import com.howard.www.business.queue.service.IQueueOfElementService;
import com.howard.www.business.service.IObtainUnprocessedOrderService;

public class HandleOrderQueueEvent extends ApplicationEvent implements IHandleOrderQueueEvent {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	protected final Logger log = LoggerFactory.getLogger(HandleOrderQueueEvent.class);
	private ApplicationContext applicationContext;
	private String orderQueueName;

	public void initHandleOrderQueueEvent(ApplicationContext applicationContext, String orderQueueName)
			throws Exception {
		this.applicationContext = applicationContext;
		this.orderQueueName = orderQueueName;
	}

	public HandleOrderQueueEvent(Object source) {
		super(source);
	}

	public void rebuildOrderQueue() throws Exception {
		log.info(Thread.currentThread().getName() + "重新开始添加" + this.orderQueueName + "订单队列start");
		IQueueOfElementService queueOfElementService = (IQueueOfElementService) applicationContext
				.getBean(this.orderQueueName + "Service");
		queueOfElementService.initializeQueueData(this.orderQueueName);
		/**
		 * 释放事件发布
		 */
		int identifierState = obtainIObtainUnprocessedOrderService().resetIdentifierState();
		log.info(Thread.currentThread().getName() + "重新构建" + this.orderQueueName + "订单队列结束重置标识位identifierState为:"
				+ identifierState);

	}

	private IObtainUnprocessedOrderService obtainIObtainUnprocessedOrderService() throws Exception {
		return (IObtainUnprocessedOrderService) applicationContext.getBean("obtainUnprocessedOrderService");
	}
}
