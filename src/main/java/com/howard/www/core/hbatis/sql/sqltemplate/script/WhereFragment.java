package com.howard.www.core.hbatis.sql.sqltemplate.script;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @ClassName:  WhereFragment   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:30:04   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class WhereFragment extends TrimFragment{
	
	private static List<String> prefixList = Arrays.asList("AND ","OR ","AND\n", "OR\n", "AND\r", "OR\r", "AND\t", "OR\t");

	public WhereFragment(SqlFragment contents) {
		super(contents, "WHERE", null,prefixList , null);
	}

}
