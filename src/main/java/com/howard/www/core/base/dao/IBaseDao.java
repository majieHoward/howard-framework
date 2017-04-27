package com.howard.www.core.base.dao;
/**
 * 
 * @ClassName:  IBaseDao   
 * @Description:TODO    
 * @author: mayijie
 * @date:   2017年2月15日 下午9:11:13   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public interface IBaseDao {
	public IQuery obtainQuery();

	public IQuery obtainQuery(String queryBeanName);

	public IInsert obtainInsert();

	public IInsert obtainInsert(String insertBeanName);

	public ISequence obtainSequence();

	public ISequence obtainSequence(String sequenceBeanName);

	public IUpdate obtainUpdate();

	public IUpdate obtainUpdate(String updateBeanName);

	public IDelete obtainDelete();

	public IDelete obtainDelete(String deleteBeanName);
}
