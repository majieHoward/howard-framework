package com.howard.www.core.base.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @ClassName:  MapObjectDepthClone   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年2月15日 下午9:15:56   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class MapObjectDepthClone {
	public static ConcurrentHashMap<String, Object> mapDeepCopy(
			ConcurrentHashMap<String, Object> mapOfPrimitive) throws Exception {
		ConcurrentHashMap<String, Object> mapOfDuplicate = new ConcurrentHashMap<String, Object>();
		int taskSize = 10;
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		CountDownLatch latch = new CountDownLatch(mapOfPrimitive.size());
		for (ConcurrentHashMap.Entry<String, Object> itemOfPrimitive : mapOfPrimitive
				.entrySet()) {
			StructureMapObjectElement mapObjectElement = new StructureMapObjectElement();
			mapObjectElement.initMapObjectElement(mapOfDuplicate,
					itemOfPrimitive, latch);
			pool.submit(mapObjectElement);
		}
		latch.await();
		pool.shutdown();
		return mapOfDuplicate;
	}
}
