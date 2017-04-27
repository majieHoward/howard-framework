package com.howard.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

@SpringBootApplication
public class ZookeeperThritfServerApplication  implements EmbeddedServletContainerCustomizer{

	public static void main(String[] args) {
		SpringApplication.run(ZookeeperThritfServerApplication.class, args);
	}
    /**
     * 
     * <p>Title: customize</p>   
     * <p>Description: 在application.proerties中配置了端口无效所以在这里实现EmbeddedServletContainerCustomizer接口</p>   
     * @param container   
     * @see org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer#customize(org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer)
     */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(9999);
		
	}
}
