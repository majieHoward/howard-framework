package com.howard.www.business.queue.dao.impl;

import org.springframework.stereotype.Repository;

import com.howard.www.business.queue.dao.IGoodsItemsOfOrderItemDao;
import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import net.sf.json.JSONArray;

@Repository("goodsItemsOfOrderItemDao")
public class GoodsItemsOfOrderItemDaoImpl extends BaseDaoImpl implements IGoodsItemsOfOrderItemDao {
	public JSONArray obtainGoodsItemsOfOrderItem(IDataTransferObject paramDto) throws Exception {
		return obtainQuery().evaluetePrimitiveSqlResource("select * from orderDetails a where a.orderId = ${orderId}")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate").evaluateIDataTransferObject(paramDto)
				.forJsonArray();
	}

}
