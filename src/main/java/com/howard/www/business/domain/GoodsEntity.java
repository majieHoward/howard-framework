package com.howard.www.business.domain;

import java.io.Serializable;

import com.howard.www.core.base.util.FrameworkStringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import net.sf.json.JSONObject;

@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodsEntity implements Serializable {
	/**
	 * @Data ：注解在类上；提供类所有属性的 getting 和 setting
	 *       方法，此外还提供了equals、canEqual、hashCode、toString 方法
	 * @Setter：注解在属性上；为属性提供 setting 方法
	 * @Getter：注解在属性上；为属性提供 getting 方法
	 * @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
	 * @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
	 * @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
	 * @Builder：注解在类上；为类提供一个内部的Builder
	 * 
	 */
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private String orderId;
	/**
	 * 货物的ID
	 */
	private String goodsId;
	/**
	 * 货物的名称
	 */
	private String goodsName;
	/**
	 * 货物货架位置
	 */
	private String goodsShelfPosition;
	/**
	 * 货物数量
	 */
	private String goodsCount;

	public GoodsEntity(JSONObject goddItemData) {
		if (goddItemData != null) {
			this.setGoodsCount(FrameworkStringUtils.asString(goddItemData.get("goodsCount")));
			this.setOrderId(FrameworkStringUtils.asString(goddItemData.get("orderId")));
			this.setGoodsId(FrameworkStringUtils.asString(goddItemData.get("goodsId")));
			this.setGoodsName(FrameworkStringUtils.asString(goddItemData.get("goodsName")));
			this.setGoodsShelfPosition(FrameworkStringUtils.asString(goddItemData.get("goodsShelfPosition")));
		}
	}
}
