package com.howard.www.business.queue.service.impl;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import org.springframework.context.ApplicationContext;

import com.howard.www.business.domain.QueueOfOrderItemEntity;
import com.howard.www.business.queue.dao.IQueueOfOrderItemsDao;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.core.data.transfer.dto.impl.DataTransferObject;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: StructureGoodItem
 * @Description:TODO 构造物品实例添加到订单实例中保存物品的向量
 * @author: mayijie
 * @date: 2017年2月16日 下午3:52:24
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class StructureOrderItem extends Thread {
	private CountDownLatch latch;
	private JSONObject orderItemData;
	private Vector<QueueOfOrderItemEntity> originalOrderElements;
	private ApplicationContext cApplicationContext;
	private String orderState;
	private String queueNameToBeOperated;

	public StructureOrderItem(CountDownLatch latch, JSONObject orderItemData,
			Vector<QueueOfOrderItemEntity> originalOrderElements, ApplicationContext cApplicationContext,
			String queueNameToBeOperated, String orderState) {
		this.latch = latch;
		this.orderItemData = orderItemData;
		this.originalOrderElements = originalOrderElements;
		this.cApplicationContext = cApplicationContext;
		this.queueNameToBeOperated=queueNameToBeOperated;
		this.orderState = orderState;
	}

	private void parsingPackageData() throws Exception {
		QueueOfOrderItemEntity orderEntity = new QueueOfOrderItemEntity(orderItemData);
		/**
		 * 修改数据库中订单状态位置为1然后再加入到原始订单队列中(同步执行)
		 */
		IDataTransferObject paramDto = new DataTransferObject();
		paramDto.evaluteRequiredParameter("stateValue", this.orderState);
		paramDto.evaluteRequiredParameter("updateOrderId", orderEntity.getOrderItemId());
		int updateRowsNum = obtainIQueueOfOrderItemsDao().evaluateOrderItemState(paramDto);
		if (updateRowsNum > 0) {
			originalOrderElements.add(orderEntity);
		}
	}

	private IQueueOfOrderItemsDao obtainIQueueOfOrderItemsDao() throws Exception {
		return (IQueueOfOrderItemsDao) cApplicationContext.getBean(this.queueNameToBeOperated+"Dao");
	}

	public void run() {
		try {
			parsingPackageData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		latch.countDown();
	}

}
