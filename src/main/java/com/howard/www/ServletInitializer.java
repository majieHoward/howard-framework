package com.howard.www;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * @ClassName: ServletInitializer
 * @Description:TODO Spring boot 容器配置需要继承 SpringBootServletInitializer
 * @author: mayijie
 * @date: 2017年3月2日 下午4:07:18
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ZookeeperThritfServerApplication.class);
	}

}
