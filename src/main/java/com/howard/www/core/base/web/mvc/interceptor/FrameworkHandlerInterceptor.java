package com.howard.www.core.base.web.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.howard.www.core.base.web.util.PojoAssignmentHelper;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.core.data.transfer.dto.impl.DataTransferObject;
/**
 * 
 * @ClassName:  FrameworkHandlerInterceptor   
 * @Description:TODO拦截所有的请求将请求的传参封装到IDataTransferObject对象中  
 * @author: mayijie
 * @date:   2017年2月9日 上午11:02:20   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class FrameworkHandlerInterceptor implements HandlerInterceptor {

	/**
	 * 
	 * <p>Title: preHandle</p>   
	 * <p>Description: </p>   
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception   
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/**
		 * handler is access Action Controller Class Object
		 */
		IDataTransferObject paramOfDto = new DataTransferObject();
		paramOfDto.evaluateRequestParams(request);
		if(HandlerMethod.class==handler.getClass()){
			/**
			 * 判断类中是否包含某个参数
			 */
			
			PojoAssignmentHelper.evaluateOneOfValueToParameter(((HandlerMethod)handler).getBean(),
					"paramOfDto", paramOfDto);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {


	}

}
