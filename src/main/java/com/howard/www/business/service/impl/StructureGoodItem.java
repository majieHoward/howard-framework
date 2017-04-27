package com.howard.www.business.service.impl;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import com.howard.www.business.domain.GoodsEntity;

import net.sf.json.JSONObject;
/**
 * 
 * @ClassName:  StructureGoodItem   
 * @Description:TODO 构造物品实例添加到订单实例中保存物品的向量
 * @author: mayijie
 * @date:   2017年2月16日 下午3:52:24   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class StructureGoodItem extends Thread {
	private CountDownLatch latch;
	private JSONObject goddItemData;
	private  Vector<GoodsEntity> goodsEntityItems;

	public StructureGoodItem(CountDownLatch latch, JSONObject goddItemData,  Vector<GoodsEntity> goodsEntityItems) {
		this.latch = latch;
		this.goddItemData = goddItemData;
		this.goodsEntityItems=goodsEntityItems;
	}
 
	private void parsingPackageData()throws Exception{
		GoodsEntity goodsEntity=new GoodsEntity(goddItemData);
		goodsEntityItems.add(goodsEntity);
	}
	public void run() {
		try{
			parsingPackageData();
		}catch(Exception e){
			e.printStackTrace();
		}
		latch.countDown();
	}

}
