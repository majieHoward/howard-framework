package com.howard.www.business.queue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.howard.www.business.domain.QueueOfOrderItemEntity;
import com.howard.www.business.queue.service.IQueueOrderPickingService;
/**
 * 
 * @ClassName:  QueueOrderPickingServiceImpl   
 * @Description:TODO 在指定位置查询订单加入到对应的队列中  
 * @author: mayijie
 * @date:   2017年2月16日 下午4:37:01   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class QueueOrderPickingServiceImpl implements IQueueOrderPickingService {
	@Autowired
	private ApplicationContext cApplicationContext;
	
	public void brushOrderToQueue(QueueOfOrderItemEntity queueOfOrderItemEntity, String queueName) throws Exception {

	}

}
