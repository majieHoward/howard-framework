package com.howard.www.core.hbatis.sql.sqltemplate.script;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @ClassName:  SetFragment   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:28:41   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class SetFragment extends TrimFragment {

	private static List<String> suffixList = Arrays.asList(",");

	public SetFragment(SqlFragment contents) {
		super(contents, "SET", null, null, suffixList);
	}

}
