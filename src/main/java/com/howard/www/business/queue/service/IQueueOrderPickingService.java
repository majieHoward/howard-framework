package com.howard.www.business.queue.service;

import com.howard.www.business.domain.QueueOfOrderItemEntity;

public interface IQueueOrderPickingService {
	public void brushOrderToQueue(QueueOfOrderItemEntity queueOfOrderItemEntity, String queueName) throws Exception;
}
