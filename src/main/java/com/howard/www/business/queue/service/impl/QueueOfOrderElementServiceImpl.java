package com.howard.www.business.queue.service.impl;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.howard.www.business.domain.QueueOfOrderItemEntity;
import com.howard.www.business.queue.dao.IQueueOfOrderItemsDao;
import com.howard.www.business.queue.dao.IQueueOfOriginalOrderItemsDao;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.core.data.transfer.dto.impl.DataTransferObject;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: QueueOfOrderElementServiceImpl
 * @Description:TODO 订单队列中的添加订单和获取订单实例
 * @author: mayijie
 * @date: 2017年2月16日 上午10:59:12
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public abstract class QueueOfOrderElementServiceImpl {
	protected final Logger log = LoggerFactory.getLogger(QueueOfOrderElementServiceImpl.class);
	@Autowired
	public ApplicationContext cApplicationContext;

	public IDataTransferObject orderStateDto(String state) throws Exception {
		IDataTransferObject paramDto = new DataTransferObject();
		paramDto.evaluteRequiredParameter("state", state);
		return paramDto;
	}
	
	public IQueueOfOrderItemsDao obtainIQueueOfOrderItemsDao(String queueNameToBeOperated)
			throws Exception {
		return (IQueueOfOrderItemsDao) cApplicationContext.getBean(queueNameToBeOperated + "Dao");
	}
	/**
	 * 抽象订单列表队列 ConcurrentLinkedQueue是非阻塞的方式来实现线程安全队列
	 * 添加一个元素的时候，它会添加到队列的尾部，当我们获取一个元素时，它会返回队列头部的元素
	 */
	private ConcurrentLinkedQueue<QueueOfOrderItemEntity> queueOfOrderItems = new ConcurrentLinkedQueue<QueueOfOrderItemEntity>();

	/**
	 * 
	 * <p>
	 * Title: appendElementToQueue
	 * </p>
	 * <p>
	 * Description: 添加一个元素的时候，它会添加到队列的尾部
	 * </p>
	 * 
	 * @param enqueueElement
	 * @throws Exception
	 * @see com.howard.www.business.queue.service.IQueueOfElementService#appendElementToQueue(java.lang.Object)
	 */
	public void appendElementToQueue(QueueOfOrderItemEntity enqueueElement) throws Exception {
		queueOfOrderItems.offer(enqueueElement);
	}

	/**
	 * 
	 * <p>
	 * Title: obtainElementFromQueue
	 * </p>
	 * <p>
	 * Description: 获取一个元素时，它会返回队列头部的元素
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see com.howard.www.business.queue.service.IQueueOfElementService#obtainElementFromQueue()
	 */
	public QueueOfOrderItemEntity obtainElementFromQueue() throws Exception {
		if (!queueOfOrderItems.isEmpty()) {
			return queueOfOrderItems.poll();
		} else {
			return null;
		}
	}

	private void structureQueueOfOriginalOrderItemsData(List<JSONObject> originalOrderItems,
			IDataTransferObject paramDto,String queueNameToBeOperated) throws Exception {

		if (originalOrderItems != null && originalOrderItems.size() > 0) {
			Vector<QueueOfOrderItemEntity> originalOrderElements = new Vector<QueueOfOrderItemEntity>();
			ThreadPoolTaskExecutor pool = (ThreadPoolTaskExecutor) cApplicationContext
					.getBean("structuralOrderThreadPool");
			CountDownLatch latch = new CountDownLatch(originalOrderItems.size());
			for (JSONObject originalOrderItem : originalOrderItems) {
				pool.submit(new StructureOrderItem(latch, originalOrderItem, originalOrderElements, cApplicationContext,
						queueNameToBeOperated,FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("state"))));
			}
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pushOrderItemsInQueue(originalOrderElements);
		}
	}

	private void pushOrderItemsInQueue(Vector<QueueOfOrderItemEntity> originalOrderElements) throws Exception {
		if (originalOrderElements != null && originalOrderElements.size() > 0) {
			for (QueueOfOrderItemEntity originalOrderElement : originalOrderElements) {
				this.appendElementToQueue(originalOrderElement);
			}
		}

	}

	public void initializeQueueData(String queueNameToBeOperated) throws Exception {
		loadQueueData(queueNameToBeOperated);
	}

	public void initializeQueueDataForSystemStartup(String queueNameToBeOperated) throws Exception {
		loadQueueDataForSystemStartup(queueNameToBeOperated);
	}

	private void structureQueue(List<JSONObject> originalOrderItems, IDataTransferObject paramDto,String queueNameToBeOperated) throws Exception {
		if (originalOrderItems != null && originalOrderItems.size() > 0) {
			structureQueueOfOriginalOrderItemsData(originalOrderItems, paramDto,queueNameToBeOperated);
		}
	}

	public void queueDataForSystemStartup(IDataTransferObject paramData, String queueNameToBeOperated)
			throws Exception {
		List<JSONObject> originalOrderItems = obtainIQueueOfOrderItemsDao(queueNameToBeOperated)
				.obtainOrderItemsForSystemStartup(new DataTransferObject());
		structureQueue(originalOrderItems, paramData,queueNameToBeOperated);
	}
	
	public void queueDataForAgain(IDataTransferObject paramData, String queueNameToBeOperated) throws Exception{
		List<JSONObject> originalOrderItems = obtainIQueueOfOrderItemsDao(queueNameToBeOperated)
				.obtainOrderItems(new DataTransferObject());
		structureQueue(originalOrderItems, paramData,queueNameToBeOperated);
	}
	
	public abstract void loadQueueData(String queueNameToBeOperated) throws Exception;

	public abstract void loadQueueDataForSystemStartup(String queueNameToBeOperated) throws Exception;

}
