package com.howard.www.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.howard.www.core.base.web.mvc.interceptor.FrameworkHandlerInterceptor;

/**
 * 
 * @ClassName:  FrameworkInterceptorConfig   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年8月22日 下午8:56:30   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
//开启Spring MVC支持，若无此句，重写WebMvcConfigurerAdapter方法无效 
//@EnableWebMvc  
@Configuration
public class FrameworkInterceptorConfig extends WebMvcConfigurerAdapter{
	protected final Logger log = LoggerFactory.getLogger(FrameworkInterceptorConfig.class);

	@Bean(name = "frameworkContextInterceptor")
	public FrameworkHandlerInterceptor initFrameworkHandlerInterceptor() throws Exception {
		return new FrameworkHandlerInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("addInterceptors(InterceptorRegistry registry)");
		try {
			registry.addInterceptor(initFrameworkHandlerInterceptor());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 
     * <p>Title: addViewControllers</p>   
     * <p>Description: </p>   
     * @param registry   
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
     */
	
	/**public void addViewControllers(ViewControllerRegistry registry) {
		log.info("执行addViewControllers(ViewControllerRegistry registry方法");
		registry.addViewController("/hospital/queue/operation/consultation/room.exhibition").setViewName("consultationRoom");
	}**/
	
}
