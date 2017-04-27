package com.howard.www.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.howard.www.core.base.web.mvc.interceptor.FrameworkHandlerInterceptor;

@Configuration
public class FrameworkInterceptorConfig extends WebMvcConfigurerAdapter{
	@Bean(name = "frameworkContextInterceptor")
	public FrameworkHandlerInterceptor initFrameworkHandlerInterceptor() throws Exception {
		return new FrameworkHandlerInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		try {
			registry.addInterceptor(initFrameworkHandlerInterceptor());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
