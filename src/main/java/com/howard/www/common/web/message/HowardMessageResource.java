package com.howard.www.common.web.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractMessageSource;

public class HowardMessageResource extends AbstractMessageSource  {
	protected final Logger log = LoggerFactory
			.getLogger(HowardMessageResource.class);
	/**
	 * Map
	 */
	protected final String MAP_SPLIT_CODE = "|";

	protected final String DB_SPLIT_CODE = "_";

	/**
	 * 当前代码中使用的是对象来保存码表中的值需要修改成从缓存服务器中查找
	 */

	/**
	 * 
	 * @return
	 */
	protected void loadTexts() {

	}

	private String getText(String code, Locale locale) {
		String localeCode = locale.getLanguage() + DB_SPLIT_CODE
				+ locale.getCountry();
		String key = code + MAP_SPLIT_CODE + localeCode;
		
		return null;
	}

	protected MessageFormat resolveCode(String code, Locale locale) {
		String msg = getText(code, locale);
		MessageFormat result = createMessageFormat(msg, locale);
		log.info(result+"");
		return result;
	}

	protected String resolveCodeWithoutArguments(String code, Locale locale) {
		String result = getText(code, locale);
		log.info(result+"");
		return result;
	}

	protected Properties getCommonMessages() {
		return super.getCommonMessages();
	}

	protected String getMessageInternal(String code, Object[] args,
			Locale locale) {
		return super.getMessageInternal(code, args, locale);
	}

	protected String getMessageFromParent(String code, Object[] args,
			Locale locale) {
		return super.getMessageFromParent(code, args, locale);
	}

	protected String getDefaultMessage(String code) {
		return super.getDefaultMessage(code);
	}

	protected String formatMessage(String msg, Object[] args, Locale locale) {
		return super.formatMessage(msg, args, locale);
	}

	

}
