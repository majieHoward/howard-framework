package com.howard.www.core.base.dao.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.howard.www.core.base.dao.ISql;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.core.data.transfer.dto.impl.DataTransferObject;
import com.howard.www.core.hbatis.sql.executor.SqlInitializationExecutor;
import com.howard.www.core.hbatis.sql.mapping.MappedStatement;

import net.sf.json.JSONObject;
/**
 * 
 * @ClassName:  AbstractSqlImpl   
 * @Description:TODO    
 * @author: mayijie
 * @date:   2017年2月15日 下午9:12:14   
 *   
 * @param <T>  
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public abstract class AbstractSqlImpl<T> implements ISql {
	protected final Logger log = LoggerFactory.getLogger(AbstractSqlImpl.class);
	@Autowired
	private ApplicationContext cApplicationContext;
	/**
	 * iDataTransferObject
	 */
	public IDataTransferObject iDataTransferObject;
	/**
	 * sqlResourceKey
	 */
	public String sqlResourceKey;
	/**
	 * sqlResource
	 */
	public String sqlResource;
	/**
	 * primitiveSqlResource
	 */
	public String primitiveSqlResource;
	/**
	 * sqlRunTime
	 */
	public String sqlRunTime;
	/**
	 * jsonNamedParameterJdbcTemplate
	 */
	public String jsonNamedParameterJdbcTemplate;

	/**
	 * 
	 * @param sqlResource
	 * @return
	 */
	public abstract T evaluateSqlResource(String sqlResource);

	/**
	 * 
	 * @param jsonNamedParameterJdbcTemplate
	 * @return
	 * @throws Exception
	 */
	public abstract T evaluateJsonNamedParameterJdbcTemplate(
			String jsonNamedParameterJdbcTemplate) throws Exception;

	/**
	 * 
	 * @param primitiveSqlResource
	 * @return
	 */
	public abstract T evaluetePrimitiveSqlResource(String primitiveSqlResource);

	public ConcurrentHashMap<String, Object> obtainMapOfRequiredParameter() {
		if (this.iDataTransferObject == null) {
			return null;
		} else {
			return iDataTransferObject.obtainMapOfRequiredParameter();
		}
	}

	public void evaluateSqlResourceStatement(String sqlResourceKey)
			throws Exception {
		if (!"".equals(FrameworkStringUtils.asString(sqlResourceKey))) {
			SqlInitializationExecutor sqlInitialization = obtainSqlInitializationExecutor("sqlInitializationExecutor");
			if (sqlInitialization != null) {
				MappedStatement mappedStatement = sqlInitialization
						.obtainAMappedStatementThroughKey(sqlResourceKey);
				if (mappedStatement != null) {
					log.info("throug {} obtain MappedStatement object {}",
							sqlResourceKey,
							JSONObject.fromObject(mappedStatement));
					analysisMappedStatement(mappedStatement);
				}
			}
		}
	}

	/**
	 * 
	 * @param mappedStatement
	 * @throws Exception
	 */
	private void analysisMappedStatement(MappedStatement mappedStatement)
			throws Exception {
		if (mappedStatement != null) {
			String paramOfPrimitiveSqlResource = FrameworkStringUtils
					.asString(mappedStatement.getResource());
			String paramOfJsonNamedParameterJdbcTemplate = FrameworkStringUtils
					.asString(mappedStatement
							.getJsonNamedParameterJdbcTemplate());
			if (!"".equals(paramOfPrimitiveSqlResource)
					&& !"".equals(paramOfJsonNamedParameterJdbcTemplate)) {
				evaluetePrimitiveSqlResource(paramOfPrimitiveSqlResource);
				evaluateJsonNamedParameterJdbcTemplate(paramOfJsonNamedParameterJdbcTemplate);

				log.info(
						"MappedStatement object primitiveSql = \"{}\" jdbcTemplate name is \"{}\"",
						paramOfPrimitiveSqlResource,
						paramOfJsonNamedParameterJdbcTemplate);
			} else {
				log.error("MappedStatement object has not attribute value");
			}
		}
	}

	public void obtainRunTimeSql(String primitiveSqlResource) throws Exception {
		if (!"".equals(primitiveSqlResource)) {
			if (this.iDataTransferObject != null) {
				SqlInitializationExecutor sqlInitialization = obtainSqlInitializationExecutor("sqlInitializationExecutor");
				String paramOfRuntimeSql = sqlInitialization
						.structureStatementThroughParam(primitiveSqlResource,
								this.obtainMapOfRequiredParameter());
				if (!"".equals(FrameworkStringUtils.asString(paramOfRuntimeSql))) {
					this.sqlResource = paramOfRuntimeSql;
					this.sqlRunTime = paramOfRuntimeSql;
					passingByValueToDTOObject();
				}
			} else {
				this.iDataTransferObject = new DataTransferObject();
			}
			
			if ("".equals(FrameworkStringUtils
					.asString(this.jsonNamedParameterJdbcTemplate))) {
				evaluateJsonNamedParameterJdbcTemplate("namedParameterJdbcTemplate");
			}
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void passingByValueToDTOObject() throws Exception {
		if (this.iDataTransferObject != null) {
			this.iDataTransferObject
					.evaluatePrimitiveSqlName(this.sqlResourceKey);
			this.iDataTransferObject.evaluateSqlStatement(this.sqlResource);
			this.iDataTransferObject
					.evaluatePrimitiveSqlStatement(this.primitiveSqlResource);
		}
	}

	/**
	 * 
	 * @param nameOfSqlInitialization
	 * @return
	 */
	private SqlInitializationExecutor obtainSqlInitializationExecutor(
			String nameOfSqlInitialization) {
		try {
			return (SqlInitializationExecutor) cApplicationContext
					.getBean(nameOfSqlInitialization);
		} catch (Exception e) {

		}
		return null;
	}

	public NamedParameterJdbcTemplate obtainJsonNamedParameterJdbcTemplate(
			String nameOfJdbctemplate) {
		try {
			return (NamedParameterJdbcTemplate) cApplicationContext
					.getBean(nameOfJdbctemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
