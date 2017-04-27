package com.howard.www.business.queue.dao;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONObject;

public interface IQueueOfOriginalOrderItemsDao {

	public int evaluateOrderItemState(IDataTransferObject queryParameters) throws Exception;

	public int obtainOrderUserMatching(IDataTransferObject queryParameters) throws Exception;

	public JSONObject obtainTotalNumberOfOrdersCompleted(IDataTransferObject queryParameters) throws Exception;

	public JSONObject obtaionIncompleteOrder(IDataTransferObject queryParameters) throws Exception;
}
