package com.howard.www.core.hbatis.sql.sqltemplate.script;

import com.howard.www.core.hbatis.sql.sqltemplate.Context;
import com.howard.www.core.hbatis.sql.sqltemplate.token.GenericTokenParser;
import com.howard.www.core.hbatis.sql.sqltemplate.token.TokenHandler;
/**
 * 
 * @ClassName:  TextFragment   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:29:12   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class TextFragment implements SqlFragment {

	private String sql;

	public TextFragment(String sql) {
		this.sql = sql;
	}

	public boolean apply(final Context context) {

		GenericTokenParser parser2 = new GenericTokenParser("${", "}",
				new TokenHandler() {

					public String handleToken(String content) {

						Object value = OgnlCache.getValue(content,
								context.getBinding());

						return value == null ? "" : value.toString();
					}
				});

		context.appendSql(parser2.parse(sql));
		return true;
	}

}
