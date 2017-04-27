package com.howard.www.business.config;

import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.howard.www.business.queue.service.IQueueOfBackOrderItemsService;
import com.howard.www.business.queue.service.IQueueOfOriginalOrderItemsService;
import com.howard.www.business.queue.service.impl.QueueOfBackOrderItemsServiceImpl;
import com.howard.www.business.queue.service.impl.QueueOfOriginalOrderItemsServiceImpl;
import com.howard.www.business.service.IInitializingBusinessDataService;
import com.howard.www.business.service.impl.InitializingBusinessDataServiceImpl;

/**
 * 
 * @ClassName: BusinessOfOrderConfig
 * @Description:TODO org.springframework.context.ApplicationContextAware
 *                   它的setApplicationContext
 *                   方法将在Spring启动之前第一个被调用。我们用来同时启动Jdon框架。
 * 
 *                   org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
 *                   它的postProcessBeanDefinitionRegistry 和
 *                   postProcessBeanFactory
 *                   方法是第二和第三被调用，它们在Bean初始化创建之前启动，如果Spring的bean需要的其他第三方中的组件，我们在这里将其注入给Spring。
 * 
 *                   org.springframework.context.ApplicationListener
 *                   用于在初始化完成后做一些事情，当Spring所有XML或元注解的Bean都启动被创建成功了，这时会调用它的唯一方法onApplicationEvent
 * @author: mayijie
 * @date: 2017年2月16日 上午11:10:04
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Configuration
public class BusinessOfOrderConfig {
	/**
	 * 
	 * @Title: initIQueueOfBackOrderItemsService @Description: TODO
	 *         获取过未被处理但是被回退的订单 @param: @return @param: @throws
	 *         Exception @return: IQueueOfBackOrderItemsService @throws
	 */
	@Bean(name = "queueOfBackOrderItemsService")
	public IQueueOfBackOrderItemsService initIQueueOfBackOrderItemsService() throws Exception {
		return new QueueOfBackOrderItemsServiceImpl();
	}

	/**
	 * 
	 * @Title: initIQueueOfOriginalOrderItemsService @Description: TODO
	 *         未被处理的订单 @param: @return @param: @throws Exception @return:
	 *         IQueueOfOriginalOrderItemsService @throws
	 */
	@Bean(name = "queueOfOriginalOrderItemsService")
	public IQueueOfOriginalOrderItemsService initIQueueOfOriginalOrderItemsService() throws Exception {
		return new QueueOfOriginalOrderItemsServiceImpl();
	}

	@Bean(name = "structuralOrderThreadPool")
	public ThreadPoolTaskExecutor initStructuralOrderThreadPool() throws Exception {
		ThreadPoolTaskExecutor structuralGoodsPool = new ThreadPoolTaskExecutor();
		/* <property name ="corePoolSize" value ="5" /> */
		structuralGoodsPool.setCorePoolSize(20);
		/* <property name ="keepAliveSeconds" value ="300" /> */
		structuralGoodsPool.setKeepAliveSeconds(300);
		/* <property name ="maxPoolSize" value ="10" /> */
		structuralGoodsPool.setMaxPoolSize(35);
		/* <property name ="queueCapacity" value ="25" /> */
		structuralGoodsPool.setQueueCapacity(25);
		/*
		 * Reject策略预定义有四种：
		 * (1)ThreadPoolExecutor.AbortPolicy策略，是默认的策略,处理程序遭到拒绝将抛出运行时
		 * RejectedExecutionException。 (2)ThreadPoolExecutor.CallerRunsPolicy策略
		 * ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃.
		 * (3)ThreadPoolExecutor.DiscardPolicy策略，不能执行的任务将被丢弃.
		 * (4)ThreadPoolExecutor.DiscardOldestPolicy策略，如果执行程序尚未关闭，
		 * 则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）.
		 */
		structuralGoodsPool.setRejectedExecutionHandler(new CallerRunsPolicy());
		return structuralGoodsPool;
	}
	@Bean(name = "structuralGoodsThreadPool")
	public ThreadPoolTaskExecutor initStructuralGoodsThreadPool() throws Exception {
		ThreadPoolTaskExecutor structuralGoodsPool = new ThreadPoolTaskExecutor();
		/* <property name ="corePoolSize" value ="5" /> */
		structuralGoodsPool.setCorePoolSize(20);
		/* <property name ="keepAliveSeconds" value ="300" /> */
		structuralGoodsPool.setKeepAliveSeconds(300);
		/* <property name ="maxPoolSize" value ="10" /> */
		structuralGoodsPool.setMaxPoolSize(35);
		/* <property name ="queueCapacity" value ="25" /> */
		structuralGoodsPool.setQueueCapacity(25);
		/*
		 * Reject策略预定义有四种：
		 * (1)ThreadPoolExecutor.AbortPolicy策略，是默认的策略,处理程序遭到拒绝将抛出运行时
		 * RejectedExecutionException。 (2)ThreadPoolExecutor.CallerRunsPolicy策略
		 * ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃.
		 * (3)ThreadPoolExecutor.DiscardPolicy策略，不能执行的任务将被丢弃.
		 * (4)ThreadPoolExecutor.DiscardOldestPolicy策略，如果执行程序尚未关闭，
		 * 则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）.
		 */
		structuralGoodsPool.setRejectedExecutionHandler(new CallerRunsPolicy());
		return structuralGoodsPool;
	}

	@Bean(name = "initializingBusinessDataService")
	public IInitializingBusinessDataService initIInitializingBusinessDataService() throws Exception {
		InitializingBusinessDataServiceImpl initializingBusinessDataService = new InitializingBusinessDataServiceImpl();
		//initializingBusinessDataService.evaluationInitializeQueueNameItems("queueOfOriginalOrderItems");
		//initializingBusinessDataService.evaluationInitializeQueueNameItems("queueOfBackOrderItems");
		return initializingBusinessDataService;
	}
}
