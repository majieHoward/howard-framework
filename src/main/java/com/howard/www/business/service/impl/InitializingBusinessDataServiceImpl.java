package com.howard.www.business.service.impl;

import java.util.Vector;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.howard.www.business.queue.service.IQueueOfElementService;
import com.howard.www.business.service.IInitializingBusinessDataService;

/**
 * 
 * @ClassName: InitializingBusinessDataServiceImpl
 * @Description:TODO 初始化业务数据
 * @author: mayijie
 * @date: 2017年2月16日 下午5:07:06
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class InitializingBusinessDataServiceImpl implements IInitializingBusinessDataService, InitializingBean {
	@Autowired
	private ApplicationContext cApplicationContext;

	private Vector<String> initializeQueueNameItems = new Vector<String>();

	public void evaluationInitializeQueueNameItems(String initializeQueueNameItem) {
		this.initializeQueueNameItems.add(initializeQueueNameItem);
	}

	private void initializingQueueData() throws Exception {
		if (initializeQueueNameItems.size() > 0) {
			IQueueOfElementService queueOfElementService;
			for (String initializeQueueNameItem : initializeQueueNameItems) {
				queueOfElementService = (IQueueOfElementService) cApplicationContext
						.getBean(initializeQueueNameItem + "Service");
				queueOfElementService.initializeQueueDataForSystemStartup(initializeQueueNameItem);
			}
		}
	}

	public void afterPropertiesSet() throws Exception {
		initializingQueueData();
	}
}
