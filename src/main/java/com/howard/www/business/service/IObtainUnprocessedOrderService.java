package com.howard.www.business.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONObject;

public interface IObtainUnprocessedOrderService {
	public JSONObject obtainOneOfUnprocessedOrder(IDataTransferObject paramDto) throws Exception;

	public JSONObject obtainOneOfUnprocessedOrderDetails(IDataTransferObject paramDto) throws Exception;

	public JSONObject evaluateCompletedObtainOrder(IDataTransferObject paramDto) throws Exception;

	public JSONObject obtainTotalNumberOfOrdersCompleted(IDataTransferObject paramDto)throws Exception;
	
	public int resetIdentifierState() throws Exception;
}
