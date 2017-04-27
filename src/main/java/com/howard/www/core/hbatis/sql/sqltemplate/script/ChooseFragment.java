package com.howard.www.core.hbatis.sql.sqltemplate.script;

import java.util.List;

import com.howard.www.core.hbatis.sql.sqltemplate.Context;
/**
 * 
 * @ClassName:  ChooseFragment   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:26:56   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class ChooseFragment implements SqlFragment {

	private SqlFragment defaultSqlFragment;
	private List<SqlFragment> ifSqlFragments;

	public ChooseFragment(List<SqlFragment> ifSqlFragments,
			SqlFragment defaultSqlFragment) {
		this.ifSqlFragments = ifSqlFragments;
		this.defaultSqlFragment = defaultSqlFragment;
	}

	public boolean apply(Context context) {
		for (SqlFragment sqlNode : ifSqlFragments) {
			if (sqlNode.apply(context)) {
				return true;
			}
		}
		if (defaultSqlFragment != null) {
			defaultSqlFragment.apply(context);
			return true;
		}
		return false;
	}

}
