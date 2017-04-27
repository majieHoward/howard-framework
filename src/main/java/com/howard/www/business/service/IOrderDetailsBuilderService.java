package com.howard.www.business.service;

import java.util.List;
import java.util.Vector;

import com.howard.www.business.domain.GoodsEntity;
import com.howard.www.business.domain.OrderDetailsEntity;
import com.howard.www.business.domain.QueueOfOrderItemEntity;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONObject;

public interface IOrderDetailsBuilderService {
	public OrderDetailsEntity structureOrderDetailsEntity(IDataTransferObject paramDto) throws Exception;

	public List<JSONObject> obtainGoodsItemsOfOrder(IDataTransferObject paramDto) throws Exception;

	public void structureOrderItemInfo(OrderDetailsEntity orderDetailsEntity, IDataTransferObject paramDto)
			throws Exception;

	public void structureGoodsItems(OrderDetailsEntity orderDetailsEntity, IDataTransferObject paramDto)
			throws Exception;

	public Vector<GoodsEntity> structureGoodsEntityItems(IDataTransferObject paramDto) throws Exception;

	public OrderDetailsEntity structureOrderDetailsEntity(QueueOfOrderItemEntity queueOfOrderItemEntity)
			throws Exception;
}
