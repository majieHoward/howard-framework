package com.howard.www.core.hbatis.sql.sqltemplate.script;

import com.howard.www.core.hbatis.sql.sqltemplate.Context;
/**
 * 
 * @ClassName:  IfFragment   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:27:50   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class IfFragment implements SqlFragment {

	private String test;

	private SqlFragment contents;

	private ExpressionEvaluator expression;

	public IfFragment(SqlFragment contents, String test) {

		this.expression = new ExpressionEvaluator();
		this.contents = contents;
		this.test = test;
	}

	public boolean apply(Context context) {
		if (expression.evaluateBoolean(test, context.getBinding())) {

			this.contents.apply(context);

			return true;
		}
		return false;
	}

}
