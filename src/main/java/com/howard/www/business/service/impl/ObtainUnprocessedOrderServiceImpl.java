package com.howard.www.business.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.howard.www.business.domain.OrderDetailsEntity;
import com.howard.www.business.domain.QueueOfOrderItemEntity;
import com.howard.www.business.domain.TotalNumberOfOrdersEntity;
import com.howard.www.business.queue.dao.IQueueOfOriginalOrderItemsDao;
import com.howard.www.business.queue.service.IQueueOfElementService;
import com.howard.www.business.service.IBusinessUserService;
import com.howard.www.business.service.IObtainUnprocessedOrderService;
import com.howard.www.business.service.IOrderDetailsBuilderService;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONObject;

@Repository("obtainUnprocessedOrderService")
public class ObtainUnprocessedOrderServiceImpl implements IObtainUnprocessedOrderService {
	@Autowired
	private ApplicationContext cApplicationContext;

	private AtomicInteger grabIdentifier = new AtomicInteger(0);

	public int resetIdentifierState() throws Exception {
		return grabIdentifier.decrementAndGet();
	}

	protected final Logger log = LoggerFactory.getLogger(ObtainUnprocessedOrderServiceImpl.class);

	public JSONObject obtainTotalNumberOfOrdersCompleted(IDataTransferObject paramDto) throws Exception {
		if (paramDto == null
				|| "".equals(FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("userId")))) {
			throw new RuntimeException("没有获取到操作人员id");
		}
		obtainIBusinessUserService().obtainUserInfoByUserId(paramDto);
		JSONObject totalValue = obtainIQueueOfOriginalOrderItemsDao("queueOfOriginalOrderItemsDao")
				.obtainTotalNumberOfOrdersCompleted(paramDto);
		if (totalValue == null) {
			throw new RuntimeException("没有查询到该操作人员的任何操作记录");
		}
		return JSONObject.fromObject(new TotalNumberOfOrdersEntity(totalValue));
	}

	/**
	 * Description: 完成一个订单
	 */
	@Transactional
	public JSONObject evaluateCompletedObtainOrder(IDataTransferObject paramDto) throws Exception {
		if (paramDto == null
				|| "".equals(FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("userId")))
				|| "".equals(
						FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("updateOrderId")))) {
			throw new RuntimeException("没有获取到操作人员工号或者订单编号");
		}
		obtainIBusinessUserService().obtainUserInfoByUserId(paramDto);
		int completedOrderNum = 0;
		int userMatchingCount = 0;
		JSONObject resultMsg = new JSONObject();
		try {
			userMatchingCount = obtainIQueueOfOriginalOrderItemsDao("queueOfOriginalOrderItemsDao")
					.obtainOrderUserMatching(paramDto);
			if (userMatchingCount != 1) {
				log.info("订单" + paramDto.obtainMapOfRequiredParameter().get("updateOrderId") + "不是操作者本次获取的订单");
				throw new RuntimeException(
						"订单" + paramDto.obtainMapOfRequiredParameter().get("updateOrderId") + "不是操作者本次获取的订单");
			}
			paramDto.evaluteRequiredParameter("stateValue", 3);
			completedOrderNum = obtainIQueueOfOriginalOrderItemsDao("queueOfOriginalOrderItemsDao")
					.evaluateOrderItemState(paramDto);
			if (completedOrderNum != 1) {
				log.info("完成订单:" + paramDto.obtainMapOfRequiredParameter().get("updateOrderId") + "更新状态时出错");
				throw new RuntimeException(
						"完成订单:" + paramDto.obtainMapOfRequiredParameter().get("updateOrderId") + "时出错");
			}
		} catch (Exception e) {
			log.info("完成订单:" + paramDto.obtainMapOfRequiredParameter().get("updateOrderId") + "时出错");
			if (FrameworkStringUtils.isEmpty(e.getMessage())) {
				throw new RuntimeException(
						"完成订单:" + paramDto.obtainMapOfRequiredParameter().get("updateOrderId") + "时出错");
			} else {
				throw new RuntimeException(e.getMessage());
			}

		}
		resultMsg.put("completedOrderNum", completedOrderNum);
		return resultMsg;
	}

	/**
	 * Description: 获取一个订单详细
	 */
	@Transactional
	public JSONObject obtainOneOfUnprocessedOrderDetails(IDataTransferObject paramDto) throws Exception {
		if (paramDto == null
				|| "".equals(FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("userId")))) {
			throw new RuntimeException("没有获取到操作人员工号");
		}
		obtainIBusinessUserService().obtainUserInfoByUserId(paramDto);
		
		
		QueueOfOrderItemEntity queueOfOrderItemEntity = obtainOneOfUnprocessedOrderEntity(paramDto);
		/**
		 * 获取到了一个订单
		 */
		if (queueOfOrderItemEntity != null) {

			try {
				/**
				 * 将订单状态位的值改为2,如果在修改过程中出现了错误马上将订单再次放到回退订单队列中
				 */
				paramDto.evaluteRequiredParameter("stateValue", 2);
				paramDto.evaluteRequiredParameter("updateOrderId", queueOfOrderItemEntity.getOrderItemId());
				obtainIQueueOfOriginalOrderItemsDao("queueOfOriginalOrderItemsDao").evaluateOrderItemState(paramDto);
				OrderDetailsEntity orderDetailsEntity = obtainIOrderDetailsBuilderService("orderDetailsBuilderService")
						.structureOrderDetailsEntity(queueOfOrderItemEntity);
				return JSONObject.fromObject(orderDetailsEntity);
			} catch (Exception e) {
				log.info("获取订单:" + queueOfOrderItemEntity.getOrderItemId() + "时出错订单将会被回退");
				obtainIQueueOfElementService("queueOfBackOrderItemsService")
						.appendElementToQueue(queueOfOrderItemEntity);
				throw new RuntimeException("获取订单:" + queueOfOrderItemEntity.getOrderItemId() + "时出错");
			}
		} else {
			throw new RuntimeException("当前没有可执行的订单请稍后再试");
		}

	}

	/**
	 * Description: 获取一个订单(只包含订单头)
	 */
	public JSONObject obtainOneOfUnprocessedOrder(IDataTransferObject paramDto) throws Exception {
		QueueOfOrderItemEntity queueOfOrderItemEntity = obtainOneOfUnprocessedOrderEntity(paramDto);
		/**
		 * 获取到了一个订单
		 */
		if (queueOfOrderItemEntity != null) {
			return JSONObject.fromObject(queueOfOrderItemEntity);
		} else {
			return null;
		}

	}

	private QueueOfOrderItemEntity obtainIncompleteOrder(IDataTransferObject paramDto)throws Exception{
		JSONObject orderItem=obtainIQueueOfOriginalOrderItemsDao("queueOfOriginalOrderItemsDao").obtaionIncompleteOrder(paramDto);
		if(orderItem!=null){
			QueueOfOrderItemEntity queueOfOrderItemEntity=new QueueOfOrderItemEntity(orderItem);
			queueOfOrderItemEntity.setOrderItemState("incomplete");
			return queueOfOrderItemEntity;
		}
		return null;
	}

	private QueueOfOrderItemEntity obtainOneOfUnprocessedOrderEntity(IDataTransferObject paramDto) throws Exception {
		/**
		 * 查看之前还没有完成订单
		 */
		QueueOfOrderItemEntity queueOfOrderItemEntity = obtainIncompleteOrder(paramDto);
		if(queueOfOrderItemEntity!=null){
			log.info(Thread.currentThread().getName() + "获取到一个之前还没有执行完成的订单");
			return queueOfOrderItemEntity;
		}
		log.info(Thread.currentThread().getName() + "不存在之前还没有完成的订单");
		/**
		 * 先从回退订单中查询
		 */
		
		queueOfOrderItemEntity = obtainUnprocessedOrderFromBackOrderItems(paramDto);
		/**
		 * 如果回退订单中没有获取到订单那么就在原始订单中获取
		 */
		if (queueOfOrderItemEntity != null) {
			log.info(Thread.currentThread().getName() + "获取到回退订单");
			return queueOfOrderItemEntity;
		}
		log.info(Thread.currentThread().getName() + "没有获取到回退订单");
		queueOfOrderItemEntity = obtainUnprocessedOrderFromOriginalOrderItems(paramDto);
		if (queueOfOrderItemEntity != null) {
			log.info(Thread.currentThread().getName() + "获取到原始订单");
			return queueOfOrderItemEntity;
		}
		log.info(Thread.currentThread().getName() + "没有获取到原始订单");
		todoPublishProcessingQueueEvents();
		return null;
	}

	private void todoPublishProcessingQueueEvents() throws Exception {
		if (grabIdentifier.get() == 0) {
			int grabIdentifierOfValue = grabIdentifier.incrementAndGet();
			log.info("准备发布过了重新获取订单队列的事件修改状态位grabIdentifier为:" + grabIdentifierOfValue);
			obtainIQueueOfElementService("queueOfOriginalOrderItemsService").publishHandlingQueueEvent();
		} else {
			log.info("已经发布过了重新获取订单队列的事件,不会再次发布事件");
		}
	}

	private QueueOfOrderItemEntity obtainUnprocessedOrderFromOriginalOrderItems(IDataTransferObject paramDto)
			throws Exception {
		return obtainUnprocessedOrderFromOrderItems("queueOfOriginalOrderItemsService");
	}

	private QueueOfOrderItemEntity obtainUnprocessedOrderFromBackOrderItems(IDataTransferObject paramDto)
			throws Exception {
		return obtainUnprocessedOrderFromOrderItems("queueOfBackOrderItemsService");
	}

	private QueueOfOrderItemEntity obtainUnprocessedOrderFromOrderItems(String queueName) throws Exception {
		IQueueOfElementService queueOfOrderItemsService = obtainIQueueOfElementService(queueName);
		if (queueOfOrderItemsService != null) {
			return (QueueOfOrderItemEntity) queueOfOrderItemsService.obtainElementFromQueue();
		}
		return null;
	}

	private IQueueOfElementService obtainIQueueOfElementService(String beanName) throws Exception {
		return (IQueueOfElementService) cApplicationContext.getBean(beanName);
	}

	private IOrderDetailsBuilderService obtainIOrderDetailsBuilderService(String beanName) throws Exception {
		return (IOrderDetailsBuilderService) cApplicationContext.getBean(beanName);
	}

	private IQueueOfOriginalOrderItemsDao obtainIQueueOfOriginalOrderItemsDao(String beanName) throws Exception {
		return (IQueueOfOriginalOrderItemsDao) cApplicationContext.getBean(beanName);
	}

	private IBusinessUserService obtainIBusinessUserService() throws Exception {
		return (IBusinessUserService) cApplicationContext.getBean("businessUserService");
	}
}
