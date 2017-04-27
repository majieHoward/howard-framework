package com.howard.www.business.domain;

import java.io.Serializable;
import java.util.Vector;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



/**
 * 
 * @ClassName: OrderDetailsEntity
 * @Description:TODO 货单详情
 * @author: mayijie
 * @date: 2017年2月16日 上午11:27:54
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDetailsEntity implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	private QueueOfOrderItemEntity orderItemInfo;
	private Vector<GoodsEntity> goodsItems;
	

	public void evaluateGoodsItems(Vector<GoodsEntity> goodsElements) {
		this.goodsItems = goodsElements;
	}
}
