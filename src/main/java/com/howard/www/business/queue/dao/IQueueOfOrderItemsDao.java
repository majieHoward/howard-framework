package com.howard.www.business.queue.dao;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;

public interface IQueueOfOrderItemsDao {
	public JSONArray obtainOrderItems(IDataTransferObject queryParameters) throws Exception;

	public JSONArray obtainOrderItemsForSystemStartup(IDataTransferObject queryParameters) throws Exception;

	public int evaluateOrderItemState(IDataTransferObject queryParameters) throws Exception;
}
