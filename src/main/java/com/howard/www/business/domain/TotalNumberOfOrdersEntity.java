package com.howard.www.business.domain;

import java.io.Serializable;

import com.howard.www.core.base.util.FrameworkStringUtils;

import net.sf.json.JSONObject;

public class TotalNumberOfOrdersEntity implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private String totalNumberOfOrders;

	public TotalNumberOfOrdersEntity(JSONObject paramDto) {
		if (paramDto != null) {
			this.setTotalNumberOfOrders(FrameworkStringUtils.asString(paramDto.get("totalNumber")));
		}
	}

	public String getTotalNumberOfOrders() {
		if (FrameworkStringUtils.isEmpty(this.totalNumberOfOrders)) {
			totalNumberOfOrders = "0";
		}
		return totalNumberOfOrders;
	}

	public void setTotalNumberOfOrders(String totalNumberOfOrders) {
		this.totalNumberOfOrders = totalNumberOfOrders;
	}

}
