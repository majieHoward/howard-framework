package com.howard.www.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.howard.www.core.hbatis.sql.executor.SqlInitializationExecutor;
/**
 * 
 * @ClassName:  SystemStartUpConfig   
 * @Description:TODO 系统启动时初始化框架  
 * @author: mayijie
 * @date:   2017年2月15日 下午9:33:04   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Configuration
public class SystemStartUpConfig {
	/**
	 * 
	 * @Title: initSqlInitializationExecutor   
	 * @Description: TODO 初始化系统所使用的sql   
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: SqlInitializationExecutor      
	 * @throws
	 */
	@Bean(name = "sqlInitializationExecutor")
	public SqlInitializationExecutor initSqlInitializationExecutor()throws Exception{
		SqlInitializationExecutor sqlInitializationExecutor= new SqlInitializationExecutor();
		return sqlInitializationExecutor;
	}
}
