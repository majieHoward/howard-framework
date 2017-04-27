package com.howard.www.core.base.tool.encoding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName:  HandleStringHelper   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年2月15日 下午9:15:42   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class HandleStringHelper {
	/**
	 * Whether the string contains Chinese
	 * 
	 * @param processingStr
	 * @return
	 */
	public static boolean stringContainsChinese(String processingStr) {
		boolean resultsOfHandle = false;
		Pattern paramOfPattern = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher paramOfMatcher = paramOfPattern.matcher(processingStr);
		if (paramOfMatcher.find()) {
			resultsOfHandle = true;
		}
		return resultsOfHandle;
	}
}
