package com.howard.www.business.queue.dao;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;

public interface IGoodsItemsOfOrderItemDao {
	public JSONArray obtainGoodsItemsOfOrderItem(IDataTransferObject paramDto) throws Exception;
}
