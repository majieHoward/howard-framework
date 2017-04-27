package com.howard.www.business.queue.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class HandleOrderQueueListener implements ApplicationListener<ApplicationEvent> {

	protected final Logger log = LoggerFactory.getLogger(HandleOrderQueueListener.class);

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof HandleOrderQueueEvent) {
			try {
				executeOrderQueueEventHandle((HandleOrderQueueEvent) event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void executeOrderQueueEventHandle(HandleOrderQueueEvent event) throws Exception {
		log.info("处理订单队列的响应事件");
		event.rebuildOrderQueue();
	}
}
