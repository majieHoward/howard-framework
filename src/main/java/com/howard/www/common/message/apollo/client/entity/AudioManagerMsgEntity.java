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
public class AudioManagerMsgEntity extends BaseMsgEntity implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	// AudioManager.STREAM_MUSIC 音乐回放即媒体音量
	// AudioManager.STREAM_RING 铃声
	// AudioManager.STREAM_ALARM 警报
	// AudioManager.STREAM_NOTIFICATION 窗口顶部状态栏通知声
	// AudioManager.STREAM_SYSTEM 系统
	// AudioManager.STREAM_VOICECALL 通话
	// AudioManager.STREAM_DTMF 双音多频,不是很明白什么东西
	private String audioKeyName;
	private String audioValue;

	public AudioManagerMsgEntity() {

	}

	public AudioManagerMsgEntity(IDataTransferObject queryParameters) {

		this.setMessageType("audioManagerProcessing");
		this.setAudioKeyName(
				FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("taskKey")));
		this.setAudioValue(
				FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("taskValue")));
		this.setEquipment(
				FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("soundSettingsEquipment")));
	}
}
