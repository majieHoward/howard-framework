package com.howard.www.business.queue.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.howard.www.business.queue.dao.IQueueOfOrderItemsDao;
import com.howard.www.business.queue.dao.IQueueOfOriginalOrderItemsDao;
import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("queueOfOriginalOrderItemsDao")
public class QueueOfOriginalOrderItemsDaoImpl extends BaseDaoImpl
		implements IQueueOfOriginalOrderItemsDao, IQueueOfOrderItemsDao {

	protected final Logger log = LoggerFactory.getLogger(QueueOfOriginalOrderItemsDaoImpl.class);

	public JSONArray obtainOrderItems(IDataTransferObject queryParameters) throws Exception {
		// OriginalOrder
		return obtainQuery().evaluetePrimitiveSqlResource("select * from OriginalOrder a where a.orderState=0")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

	public JSONArray obtainOrderItemsForSystemStartup(IDataTransferObject queryParameters) throws Exception {
		// OriginalOrder
		return obtainQuery()
				.evaluetePrimitiveSqlResource("select * from OriginalOrder a where a.orderState=0 or a.orderState=1")
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

	public int obtainOrderUserMatching(IDataTransferObject queryParameters) throws Exception {
		JSONArray userMatchingItems = obtainQuery()
				.evaluetePrimitiveSqlResource(
						"select * from OriginalOrder a where a.orderId=${updateOrderId} and a.processingId=${userId}")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
		if (userMatchingItems != null) {
			return userMatchingItems.size();
		}
		return 0;
	}

	public JSONObject obtainTotalNumberOfOrdersCompleted(IDataTransferObject queryParameters) throws Exception {
		return obtainQuery()
				.evaluetePrimitiveSqlResource(
						"select count(*) as totalNumber from originalorder a where date_format(a.orderStateTime,'%Y-%m-%d')=curdate() and a.orderState=3 "
								+ "<if test='userId != null ' > and a.processingId=${userId}</if>")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonObject();
	}

	@Override
	public JSONObject obtaionIncompleteOrder(IDataTransferObject queryParameters) throws Exception {
		// OriginalOrder
		return obtainQuery()
				.evaluetePrimitiveSqlResource(
						"select * from OriginalOrder a where a.orderState=2 <if test='userId != null ' > and a.processingId=${userId}</if>")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonObject();
	}
}
