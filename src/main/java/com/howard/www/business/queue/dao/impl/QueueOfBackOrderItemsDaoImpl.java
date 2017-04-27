package com.howard.www.business.queue.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.howard.www.business.queue.dao.IQueueOfBackOrderItemsDao;
import com.howard.www.business.queue.dao.IQueueOfOrderItemsDao;
import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;

@Repository("queueOfBackOrderItemsDao")
public class QueueOfBackOrderItemsDaoImpl extends BaseDaoImpl
		implements IQueueOfBackOrderItemsDao, IQueueOfOrderItemsDao {

	protected final Logger log = LoggerFactory.getLogger(QueueOfBackOrderItemsDaoImpl.class);
	
	public JSONArray obtainOrderItems(IDataTransferObject queryParameters) throws Exception {
		// BackOrder
		return obtainQuery().evaluetePrimitiveSqlResource("select * from OriginalOrder a where a.orderState=4")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

	public JSONArray obtainOrderItemsForSystemStartup(IDataTransferObject queryParameters) throws Exception {
		// BackOrder
		return obtainQuery()
				.evaluetePrimitiveSqlResource("select * from OriginalOrder a where a.orderState=4 or a.orderState=5")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

	public int evaluateOrderItemState(IDataTransferObject queryParameters) throws Exception {
		int updateRowsNum = obtainUpdate()
				.evaluetePrimitiveSqlResource(
						"UPDATE originalorder SET orderState=${stateValue}, orderStateTime=now() <if test='userId != null ' >,processingId=${userId}</if> WHERE orderId =${updateOrderId}")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).modifyRecord();
		log.info(updateRowsNum + "条订单编号为:" + queryParameters.obtainMapOfRequiredParameter().get("updateOrderId")
				+ "的订单状态被改变成为了" + queryParameters.obtainMapOfRequiredParameter().get("stateValue"));
		return updateRowsNum;
	}

}
