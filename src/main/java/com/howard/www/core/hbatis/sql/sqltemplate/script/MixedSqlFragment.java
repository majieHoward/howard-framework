package com.howard.www.core.hbatis.sql.sqltemplate.script;

import java.util.List;

import com.howard.www.core.hbatis.sql.sqltemplate.Context;

/**
 * 
 * @ClassName:  MixedSqlFragment   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:28:09   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class MixedSqlFragment implements SqlFragment {
	
	private List<SqlFragment> contents ;
	
	public MixedSqlFragment(List<SqlFragment> contents){
		this.contents  = contents ;
	}

	public boolean apply(Context context) {
		
		for(SqlFragment sf : contents){
			sf.apply(context);
		}
		
		return true;
	}
	
	
	
	

}
