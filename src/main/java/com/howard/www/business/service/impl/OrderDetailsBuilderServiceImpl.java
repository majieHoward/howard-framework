package com.howard.www.business.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;

import com.howard.www.business.domain.GoodsEntity;
import com.howard.www.business.domain.GoodsEntityComparator;
import com.howard.www.business.domain.OrderDetailsEntity;
import com.howard.www.business.domain.QueueOfOrderItemEntity;
import com.howard.www.business.queue.dao.IGoodsItemsOfOrderItemDao;
import com.howard.www.business.service.IOrderDetailsBuilderService;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.core.data.transfer.dto.impl.DataTransferObject;

import net.sf.json.JSONObject;

@Repository("orderDetailsBuilderService")
public class OrderDetailsBuilderServiceImpl implements IOrderDetailsBuilderService {
	@Autowired
	private ApplicationContext cApplicationContext;

	public OrderDetailsEntity structureOrderDetailsEntity(QueueOfOrderItemEntity queueOfOrderItemEntity)
			throws Exception {
		if (queueOfOrderItemEntity == null || FrameworkStringUtils.isEmpty(queueOfOrderItemEntity.getOrderItemId())) {
			throw new RuntimeException();
		}
		OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
		orderDetailsEntity.setOrderItemInfo(queueOfOrderItemEntity);
		IDataTransferObject paramDto = new DataTransferObject();
		paramDto.evaluteRequiredParameter("orderId", queueOfOrderItemEntity.getOrderItemId());
		structureGoodsItems(orderDetailsEntity, paramDto);
		return orderDetailsEntity;

	}

	public OrderDetailsEntity structureOrderDetailsEntity(IDataTransferObject paramDto) throws Exception {
		if (paramDto == null
				|| "".equals(FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("orderId")))) {
			throw new RuntimeException();
		}
		OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
		structureOrderItemInfo(orderDetailsEntity, paramDto);
		structureGoodsItems(orderDetailsEntity, paramDto);
		return orderDetailsEntity;
	}

	public void structureOrderItemInfo(OrderDetailsEntity orderDetailsEntity, IDataTransferObject paramDto)
			throws Exception {
		orderDetailsEntity.setOrderItemInfo(new QueueOfOrderItemEntity(null));
	}

	public void structureGoodsItems(OrderDetailsEntity orderDetailsEntity, IDataTransferObject paramDto)
			throws Exception {
		orderDetailsEntity.evaluateGoodsItems(structureGoodsEntityItems(paramDto));
	}

	public List<JSONObject> obtainGoodsItemsOfOrder(IDataTransferObject paramDto) throws Exception {
		IGoodsItemsOfOrderItemDao goodsItemsOfOrderItemDao = (IGoodsItemsOfOrderItemDao) cApplicationContext
				.getBean("goodsItemsOfOrderItemDao");
		if (goodsItemsOfOrderItemDao != null) {
			return goodsItemsOfOrderItemDao.obtainGoodsItemsOfOrderItem(paramDto);
		}
		return null;
	}

	public Vector<GoodsEntity> structureGoodsEntityItems(IDataTransferObject paramDto) throws Exception {
		Vector<GoodsEntity> goodsEntityItems = new Vector<GoodsEntity>();
		List<JSONObject> paramGoodsItems = obtainGoodsItemsOfOrder(paramDto);
		if (paramGoodsItems != null && paramGoodsItems.size() > 0) {
			ThreadPoolTaskExecutor pool = (ThreadPoolTaskExecutor) cApplicationContext
					.getBean("structuralGoodsThreadPool");
			CountDownLatch latch = new CountDownLatch(paramGoodsItems.size());
			for (JSONObject paramGoodsItem : paramGoodsItems) {
				pool.submit(new StructureGoodItem(latch, paramGoodsItem, goodsEntityItems));
			}
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		GoodsEntityComparator goodsEntityComparator = new GoodsEntityComparator();
		Collections.sort(goodsEntityItems, goodsEntityComparator);
		return goodsEntityItems;
	}
}
