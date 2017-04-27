package com.howard.www.common.message.apollo.client.entity;

import java.io.Serializable;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SilentInstallMsgEntity extends BaseMsgEntity implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private String apkName;
	private String apkNetworkAddress;
	private String versionCode;
	private String versionName;
	private String applicationPackageName;
	private String isAutoStartUp;

	public SilentInstallMsgEntity() {

	}

	public SilentInstallMsgEntity(IDataTransferObject queryParameters) {
		this.setMessageType("silentInstallProcessing");
		this.setApkName(FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("apkName")));
		this.setApkNetworkAddress(
				FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("apkNetworkAddress")));
		this.setVersionName(
				FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("versionName")));
		this.setVersionCode(
				FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("versionCode")));
	    this.setEquipment( FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("installEquipment")));
	    this.setApplicationPackageName(FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("packageName")));
	}
}
