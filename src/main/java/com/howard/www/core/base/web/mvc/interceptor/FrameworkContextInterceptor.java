package com.howard.www.core.base.web.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.howard.www.core.base.web.util.PojoAssignmentHelper;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.core.data.transfer.dto.impl.DataTransferObject;

/**
 * 
 * @ClassName:  FrameworkContextInterceptor   
 * @Description:TODO拦截所有的请求将请求的传参封装到IDataTransferObject对象中   
 * @author: mayijie
 * @date:   2017年2月9日 上午11:03:19   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class FrameworkContextInterceptor extends HandlerInterceptorAdapter {
	
	protected final Logger log = LoggerFactory
			.getLogger(FrameworkContextInterceptor.class);

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
    /**
     * 
     * <p>Title: preHandle</p>   
     * <p>Description: </p>   
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception   
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/**
		 * handler is access Action Controller Class Object
		 */
		IDataTransferObject paramOfDto = new DataTransferObject();
		paramOfDto.evaluateRequestParams(request);
		if(HandlerMethod.class==handler.getClass()){
			PojoAssignmentHelper.evaluateOneOfValueToParameter(((HandlerMethod)handler).getBean(),
					"paramOfDto", paramOfDto);
		}
		return super.preHandle(request, response, handler);
	}

}
