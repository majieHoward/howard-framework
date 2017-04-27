package com.howard.www.core.hbatis.sql.sqltemplate;

import java.util.HashMap;
/**
 * 
 * @ClassName:  Bindings   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:25:53   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class Bindings extends HashMap<Object,Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7290846439659491933L;

	public Bindings bind(Object key , Object value ){
		this.put(key, value) ;
		
		return this; 
	}

}
