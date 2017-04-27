package com.howard.www.business.queue.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howard.www.business.domain.QueueOfOrderItemEntity;
import com.howard.www.business.queue.service.IQueueOfBackOrderItemsService;
import com.howard.www.business.queue.service.IQueueOfElementService;

/**
 * 
 * @ClassName: QueueOfOrderItemsServiceImpl
 * @Description:TODO 被获取过但是没有被处理回退的订单列表 ConcurrentLinkedQueue是非阻塞的方式来实现线程安全队列
 *                   添加一个元素的时候，它会添加到队列的尾部，当我们获取一个元素时，它会返回队列头部的元素
 * 
 * @author: mayijie
 * @date: 2017年2月16日 上午10:28:32
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class QueueOfBackOrderItemsServiceImpl extends QueueOfOrderElementServiceImpl
		implements IQueueOfElementService<QueueOfOrderItemEntity>, IQueueOfBackOrderItemsService {
	protected final Logger log = LoggerFactory.getLogger(QueueOfBackOrderItemsServiceImpl.class);

	public void loadQueueData(String queueNameToBeOperated) throws Exception {
		log.info("获取未处理订单将其添加到BackOrderItems中");
		queueDataForAgain(orderStateDto("5"), queueNameToBeOperated);
	}

	public void publishHandlingQueueEvent() throws Exception {

	}

	public void loadQueueDataForSystemStartup(String queueNameToBeOperated) throws Exception {
		log.info("系统启动时获取未处理订单将其添加到BackOrderItems中");
		queueDataForSystemStartup(orderStateDto("5"), queueNameToBeOperated);
	}

}
