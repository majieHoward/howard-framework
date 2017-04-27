package com.howard.www.business.dao.impl;

import org.springframework.stereotype.Repository;
import com.howard.www.business.dao.IBusinessUserDao;
import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("businessUserDao")
public class BusinessUserDaoImpl extends BaseDaoImpl implements IBusinessUserDao {

	public JSONArray obtainUserInfo(IDataTransferObject queryParameters) throws Exception {
		return obtainQuery()
				.evaluetePrimitiveSqlResource(
						"select * from orderUser  where 1=1 <if test='userName != null ' > and username='${userName}'</if> <if test='userPassword != null ' > and password='${userPassword}'</if>")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

	@Override
	public JSONObject obtainUserInfoByUserId(IDataTransferObject queryParameters) throws Exception {
		return obtainQuery()
				.evaluetePrimitiveSqlResource(
						"select * from orderUser  where 1=1 <if test='userId != null ' > and userId='${userId}'</if>")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonObject();
	}

}
