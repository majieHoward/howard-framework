package com.howard.www.core.hbatis.datasource.jdbctemplate;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.hbatis.datasource.FrameworkDynamicDataSource;

/**
 * 
 * @ClassName: FrameworkSwitchDataSourceOfJdbcTemplate
 * @Description:TODO 切换JdbcTemplate的数据源
 * @author: mayijie
 * @date: 2017年2月13日 下午12:25:42
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class FrameworkSwitchDataSourceOfJdbcTemplate {
	private static String DEFAULT_DATA_SOURCE = "systemDataSource";

	/**
	 * 
	 * @Title: setDataSourceName   
	 * @Description: TODO 设置需要用的数据源名称
	 * @param: @param dataSourceName      
	 * @return: void      
	 * @throws
	 */
	public static void setDataSourceName(String dataSourceName) {
		FrameworkDynamicDataSource.setDynamicDatasourceItems(getDataSourceName(dataSourceName));
	}

	/**
	 * 
	 * @Title: getDataSourceName   
	 * @Description: TODO 获取数据源名称   
	 * @param: @param dataSourceName
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getDataSourceName(String dataSourceName) {
		String dataSource = dataSourceName;
		if (FrameworkStringUtils.isEmpty(dataSource)) {
			dataSource = DEFAULT_DATA_SOURCE;
		}

		return dataSource;
	}
}
