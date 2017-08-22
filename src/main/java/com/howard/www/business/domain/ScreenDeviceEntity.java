package com.howard.www.business.domain;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @ClassName: ScreenDeviceEntity
 * @Description:TODO(屏幕设备信息放入到redis或cache中,屏幕的IP地址作为Key,ScreenDeviceEntity作为Value)
 * @author: mayijie
 * @date: 2017年8月21日 上午10:18:38
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenDeviceEntity implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	/**
	 * ANDROID_ID 是设备首次启动时由系统随机生成的一串64位的十六进制数字.
	 * 设备刷机wipe数据或恢复出厂设置时ANDROID_ID值会被重置. 现在网上已有修改设备ANDROID_ID值的APP应用.
	 * 某些厂商定制的系统可能会导致不同的设备产生相同的ANDROID_ID. 某些厂商定制的系统可能导致设备返回ANDROID_ID值为空.
	 * CDMA设备，ANDROID_ID和DeviceId返回的值相同.
	 **/
	/**
	 * MEI号（国际移动设备身份码）、IMSI号（国际移动设备识别码）这两个是有电话功能的移动设备才具有,
	 * 也就是说某些没有电话功能的平板是获取不到IMEI和IMSI号的. 且在某些设备上getDeviceId()会返回垃圾数据
	 */
	private static final long serialVersionUID = 1L;
	// eg.171.217.95.14
	private String internetProtocol;
	// eg.351521004992889
	private String screenDeviceIdentity;
	/**
	 * Serial Number在一些没有电话功能的设备会提供，某些手机上也可能提供(所以就是经常会返回Unknown)
	 */
	/**
	 * 序列号SerialNumber 从Android 2.3开始，通过android.os.Build.SERIAL方法可获取到一个序列号
	 * 没有电话功能的设备也都需要上给出此唯一的序列号 在某些设备上此方法会返回垃圾数据
	 */
	private String serialNumber;
	/**
	 * ANDROID_ID 是设备首次启动时由系统随机生成的一串64位的十六进制数字.
	 * 设备刷机wipe数据或恢复出厂设置时ANDROID_ID值会被重置. 现在网上已有修改设备ANDROID_ID值的APP应用.
	 * 某些厂商定制的系统可能会导致不同的设备产生相同的ANDROID_ID. 某些厂商定制的系统可能导致设备返回ANDROID_ID值为空.
	 * CDMA设备，ANDROID_ID和DeviceId返回的值相同.
	 **/
	private String androidId;
	// eg.A1524
	private ScreenDeviceSpecificationEntity specification;
	// 是否可用'10A' '10X'
	private String available;
}
