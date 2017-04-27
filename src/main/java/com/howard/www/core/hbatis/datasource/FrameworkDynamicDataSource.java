package com.howard.www.core.hbatis.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.howard.www.core.base.util.FrameworkStringUtils;
/**
 * 
 * @ClassName:  FrameworkDynamicDataSource   
 * @Description:TODO 设置动态数据源
 * @author: mayijie
 * @date:   2017年2月13日 上午10:44:29   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class FrameworkDynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 
     */
    private static final ThreadLocal<String> DYNAMIC_DATASOURCE_ITEMS = new ThreadLocal<String>();  

    private String DEFAULT_DATA_SOURCE="systemDataSource";
    /**
     * 返回实际要使用的数据源的key也即在前面配置的数据源bean的ID
     * <p>Title: determineCurrentLookupKey</p>   
     * <p>Description: </p>   
     * @return   
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
	@Override
	protected Object determineCurrentLookupKey() {
		String dynamicDataSource = DYNAMIC_DATASOURCE_ITEMS.get();  
        if (FrameworkStringUtils.isEmpty(dynamicDataSource)) {  
        	dynamicDataSource = DEFAULT_DATA_SOURCE; 
            DYNAMIC_DATASOURCE_ITEMS.set(dynamicDataSource);  
        }   
        System.out.println("DYNAMIC_DATASOURCE_ITEMS:"+DYNAMIC_DATASOURCE_ITEMS);
        return dynamicDataSource;  
	}
	/**
	 * 
	 * @Title: getDynamicDatasourceItems   
	 * @Description: TODO 
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getDynamicDatasourceItems() {
		return DYNAMIC_DATASOURCE_ITEMS.get();
	}
	/**
	 * 
	 * @Title: setDynamicDatasourceItems   
	 * @Description: TODO 
	 * @param: @param dynamicDataSourceItem      
	 * @return: void      
	 * @throws
	 */
    public static void setDynamicDatasourceItems(String dynamicDataSourceItem){
    	DYNAMIC_DATASOURCE_ITEMS.set(dynamicDataSourceItem);
    }
	
	
}
