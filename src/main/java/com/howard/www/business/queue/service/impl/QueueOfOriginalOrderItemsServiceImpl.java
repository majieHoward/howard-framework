package com.howard.www.business.queue.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howard.www.business.domain.QueueOfOrderItemEntity;
import com.howard.www.business.queue.event.HandleOrderQueueEvent;
import com.howard.www.business.queue.event.HandleOrderQueueEventPubish;
import com.howard.www.business.queue.service.IQueueOfElementService;
import com.howard.www.business.queue.service.IQueueOfOriginalOrderItemsService;

/**
 * 
 * @ClassName: QueueOfOrderItemsServiceImpl
 * @Description:TODO 原始的未处理订单列表 ConcurrentLinkedQueue是非阻塞的方式来实现线程安全队列
 *                   添加一个元素的时候，它会添加到队列的尾部，当我们获取一个元素时，它会返回队列头部的元素
 * @author: mayijie
 * @date: 2017年2月16日 上午10:28:32
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class QueueOfOriginalOrderItemsServiceImpl extends QueueOfOrderElementServiceImpl
		implements IQueueOfElementService<QueueOfOrderItemEntity>, IQueueOfOriginalOrderItemsService {
	protected final Logger log = LoggerFactory.getLogger(QueueOfOriginalOrderItemsServiceImpl.class);

	public void loadQueueDataForSystemStartup(String queueNameToBeOperated) throws Exception {
		log.info("系统启动时获取未处理订单将其添加到OriginalOrderItems中");
		queueDataForSystemStartup(orderStateDto("1"), queueNameToBeOperated);
	}
	
	public void loadQueueData(String queueNameToBeOperated) throws Exception {
		log.info("获取未处理订单将其添加到OriginalOrderItems中");
		queueDataForAgain(orderStateDto("1"), queueNameToBeOperated);
	}

	/**
	 * Description: 重新添加原始的未处理订单列表
	 */
	public void publishHandlingQueueEvent() throws Exception {
		HandleOrderQueueEvent handleOrderQueueEvent = new HandleOrderQueueEvent("queue");
		handleOrderQueueEvent.initHandleOrderQueueEvent(cApplicationContext, "queueOfOriginalOrderItems");
		obtainHandleOrderQueueEventPubish().publishHandleOrderQueueEvent(handleOrderQueueEvent);
	}

	private HandleOrderQueueEventPubish obtainHandleOrderQueueEventPubish() throws Exception {
		return (HandleOrderQueueEventPubish) cApplicationContext.getBean("handleOrderQueueEventPubish");
	}

}
