package com.howard.www.core.base.web.mvc.argument;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.core.data.transfer.dto.impl.DataTransferObject;
/**
 * 
 * @ClassName:  IDataTransferObjectMethodArgumentResolver   
 * @Description:TODO(用于在给定请求的上下文中将方法参数解析为参数值的策略接口
 * 将请求参数封装到一个IDataTransferObject对象中)   
 * @author: mayijie
 * @date:   2017年8月21日 下午5:14:45   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class IDataTransferObjectMethodArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * 
     * <p>Title: supportsParameter</p>   
     * <p>Description: 该解析器是否支持给定的方法参数。</p>   
     * @param parameter
     * @return   
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
     */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		 return IDataTransferObject.class.isAssignableFrom(parameter.getParameterType());
	}
    /**
     * 
     * <p>Title: resolveArgument</p>   
     * <p>Description: 从给定的请求中将方法参数解析为参数值。</p>   
     * @param parameter 要解决的方法参数。此参数之前必须已传递给supportsParameter(org.springframework.core.MethodParameter)必须已返回的参数true。
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return 解析的参数值，或 null
     * @throws Exception   
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
     */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		/**
		 * dataTransferObject
		 */
		IDataTransferObject dataTransferObject = new DataTransferObject();
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		dataTransferObject.evaluateRequestParams(request);
		return dataTransferObject;
	}

}
