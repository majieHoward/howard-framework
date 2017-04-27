package com.howard.www.core.base.web.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;

import com.howard.www.core.base.util.FrameworkStringUtils;

/**
 * 
 * @ClassName: PojoAssignmentHelper
 * @Description:TODO通过反射的原来调用JavaBean的中get与set方法赋值已经获取值
 * @author: mayijie
 * @date: 2017年2月9日 上午11:06:20
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class PojoAssignmentHelper {
	/**
	 * 
	 * @Title: obtainOneOfParameterValues @Description:
	 * TODO通过JavaBean中的get方法获得JavaBean中的某个属性的值 @param: @param
	 * originalPojoBeanObject @param: @param methodName @param: @param
	 * methodParameter @param: @return @param: @throws Exception @return:
	 * String @throws
	 */
	public static String obtainOneOfParameterValues(final Object originalPojoBeanObject, final String methodName,
			final Object methodParameter) throws Exception {
		if (methodName == null || originalPojoBeanObject == null) {
			return "";
		}
		// eventIdentification 得到方法名获得具体的方法
		Method methodOfIsEventIdentification = originalPojoBeanObject.getClass().getMethod(methodName);
		if (methodOfIsEventIdentification == null) {
			return null;
		}
		if (methodParameter == null) {
			Object isEventIdentification = methodOfIsEventIdentification.invoke(originalPojoBeanObject, null);
			String paramIsEvent = FrameworkStringUtils.asString(isEventIdentification);
			return paramIsEvent;
		}
		return null;
	}

	/**
	 * 
	 * @Title: evaluateOneOfValueToParameter @Description:
	 * TODO执行JAVABEAN中的Set方法为某个属性赋值 @param: @param
	 * originalPojoBeanObject @param: @param paramName @param: @param
	 * methodParameter @param: @throws Exception @return: void @throws
	 */
	@SuppressWarnings("rawtypes")
	public static void evaluateOneOfValueToParameter(Object originalPojoBeanObject, String paramName,
			Object methodParameter) throws Exception {
		Class<?> clazz = null;
		clazz = originalPojoBeanObject.getClass();

		// 使用符合JavaBean规范的属性访问器
		PropertyDescriptor pd = new PropertyDescriptor(paramName, clazz);
		// 调用setter
		Method writeMethod = pd.getWriteMethod();

		Class[] parameterTypes = writeMethod.getParameterTypes();
		/**
		 * 如果是基本数据类型时（如int、float、double、byte、char、boolean）
		 * 需要先将Object转换成相应的封装类之后再转换成对应的基本数据类型 否则会报 ClassCastException
		 **/
		if (parameterTypes[0] == int.class) {
			writeMethod.invoke(originalPojoBeanObject, Integer.parseInt(methodParameter.toString()));

		} else if (parameterTypes[0] == float.class) {
			writeMethod.invoke(originalPojoBeanObject, ((Float) methodParameter).floatValue());

		} else if (parameterTypes[0] == double.class) {
			writeMethod.invoke(originalPojoBeanObject, ((Double) methodParameter).doubleValue());

		} else if (parameterTypes[0] == byte.class) {
			writeMethod.invoke(originalPojoBeanObject, ((Byte) methodParameter).byteValue());

		} else if (parameterTypes[0] == char.class) {
			writeMethod.invoke(originalPojoBeanObject, ((Character) methodParameter).charValue());

		} else if (parameterTypes[0] == boolean.class) {
			writeMethod.invoke(originalPojoBeanObject, ((Boolean) methodParameter).booleanValue());

		} else {
			writeMethod.invoke(originalPojoBeanObject, methodParameter);
		}

	}
}
