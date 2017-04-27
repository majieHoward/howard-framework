package com.howard.www.core.base.dao;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

/**
 * 
 * @ClassName:  IHandleSql   
 * @Description:TODO   
 * @author: mayijie
 * @date:   2017年2月15日 下午9:15:18   
 *   
 * @param <T>  
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public interface IHandleSql<T> {
	/**
	 * 
	 * @param sqlResource
	 * @return
	 */
	public T evaluateSqlResource(String sqlResource);

	/**
	 * 
	 * @param iDataTransferObject
	 * @return
	 */
	public T evaluateIDataTransferObject(IDataTransferObject iDataTransferObject);

	/**
	 * 
	 * @param sqlResourceKey
	 * @return
	 */
	public T evaluateSqlResourceKey(String sqlResourceKey) throws Exception;

	public T evaluetePrimitiveSqlResource(String primitiveSqlResource);

	public T evaluateJsonNamedParameterJdbcTemplate(
			String jsonNamedParameterJdbcTemplate) throws Exception;
}
