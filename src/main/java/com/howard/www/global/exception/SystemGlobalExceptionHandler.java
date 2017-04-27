package com.howard.www.global.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howard.www.business.domain.BackInteractivenfoEntity;
import com.howard.www.core.base.util.FrameworkStringUtils;

@ControllerAdvice
public class SystemGlobalExceptionHandler {

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public BackInteractivenfoEntity jsonErrorHandler(HttpServletRequest request, RuntimeException e) throws Exception {
		BackInteractivenfoEntity backInteractivenfoEntity = new BackInteractivenfoEntity();
		backInteractivenfoEntity.setIsSuccess("error");
		backInteractivenfoEntity.setInteractiveMessage(e.getMessage());
		backInteractivenfoEntity.setRequestAddress(FrameworkStringUtils.asString(request.getRequestURL().toString()));
		return backInteractivenfoEntity;
	}
}
