package com.howard.www.core.base.web.util;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import com.howard.www.core.base.tool.encoding.HandleChineseHelper;
import com.howard.www.core.base.util.FrameworkStringUtils;
/**
 * 
 * @ClassName:  RequestHelper   
 * @Description:TODO 获取请求参数的变量名和变量值添加到Map中
 * @author: mayijie
 * @date:   2017年2月9日 上午11:14:13   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class RequestHelper {
	/**
	 * 
	 * @Title: conversionRequestParameter   
	 * @Description: TODO   
	 * @param: @param request
	 * @param: @return      
	 * @return: ConcurrentHashMap<String,Object>      
	 * @throws
	 */
	public static ConcurrentHashMap<String, Object> conversionRequestParameter(
			final HttpServletRequest request) {
		ConcurrentHashMap<String, Object> mapOfRequestParameter = new ConcurrentHashMap<String, Object>();
		Enumeration<String> paramEnumsOfRequest = request.getParameterNames();
		String paramOfName = null;
		String paramOfValue = null;
		while (paramEnumsOfRequest.hasMoreElements()) {
			paramOfName = FrameworkStringUtils.asString(paramEnumsOfRequest
					.nextElement());
			paramOfValue = FrameworkStringUtils.asString(request
					.getParameter(paramOfName));
			if (!"".equals(paramOfValue) && !"".equals(paramOfName)) {
				mapOfRequestParameter.put(paramOfName,
						HandleChineseHelper.toChinese(paramOfValue));
			}
		}
		return mapOfRequestParameter;
	}
}
