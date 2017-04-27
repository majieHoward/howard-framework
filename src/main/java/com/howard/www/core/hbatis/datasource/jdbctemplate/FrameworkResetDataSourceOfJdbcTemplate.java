package com.howard.www.core.hbatis.datasource.jdbctemplate;



import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.hbatis.datasource.FrameworkDynamicDataSource;

/**
 * 
 * @ClassName: FrameworkResetDataSourceOfJdbcTemplate
 * @Description:TODO 发生切换数据源的情况时重设JdbcTemplate数据源
 * @author: mayijie
 * @date: 2017年2月13日 上午11:11:22
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class FrameworkResetDataSourceOfJdbcTemplate implements ApplicationContextAware {
	@Autowired
	private JdbcTemplate systemJdbcTemplate;

	private ApplicationContext hApplicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.hApplicationContext = applicationContext;
	}

	public JdbcTemplate obtainOperateJdbcTemplate() {
		String nameOfdataSource = FrameworkDynamicDataSource.getDynamicDatasourceItems();
		DruidDataSource dynamicDruidDataSource=null;
		if (!FrameworkStringUtils.isEmpty(nameOfdataSource)) {
			dynamicDruidDataSource=(DruidDataSource) hApplicationContext.getBean(nameOfdataSource);
			systemJdbcTemplate.setDataSource(dynamicDruidDataSource);
			System.out.println(dynamicDruidDataSource.getUrl());
		}
		
		return systemJdbcTemplate;
	}

	public void evaluateOperateJdbcTemplate(JdbcTemplate systemJdbcTemplate) {
		this.systemJdbcTemplate = systemJdbcTemplate;
	}

}
