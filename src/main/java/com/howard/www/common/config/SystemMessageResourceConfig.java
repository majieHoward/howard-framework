package com.howard.www.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.howard.www.common.web.message.HowardMessageResource;

@Configuration
public class SystemMessageResourceConfig {
	@Bean(name = "messageSource")  
	public HowardMessageResource initHowardMessageResource(){
		return new HowardMessageResource();
		
	}
}
