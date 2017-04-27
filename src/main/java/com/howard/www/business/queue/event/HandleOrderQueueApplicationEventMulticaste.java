package com.howard.www.business.queue.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName:  HandleOrderQueueApplicationEventMulticaste   
 * @Description:TODO 名字必须是applicationEventMulticaster和messageSource是一样的，默认找这个名字的对象
 *                   如果找不到就new一个，但不是异步调用而是同步调用  
 *                   缺点是全局的，要么全部异步，要么全部同步
 * @author: mayijie
 * @date:   2017年2月17日 下午10:51:23   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Repository("applicationEventMulticaster")
public class HandleOrderQueueApplicationEventMulticaste extends SimpleApplicationEventMulticaster {
	private TaskExecutor taskExecutor = new TaskExecutor() {
		ExecutorService exeserv = Executors.newCachedThreadPool();

		public void execute(Runnable task) {
			exeserv.execute(task);
		}
	};

	protected TaskExecutor getTaskExecutor() {
		return this.taskExecutor;
	}
}
