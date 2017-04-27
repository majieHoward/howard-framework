package com.howard.www.business.service.impl;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.howard.www.business.dao.IBusinessUserDao;
import com.howard.www.business.domain.EquipmentRichselectEntity;
import com.howard.www.business.domain.LoginInfoEntity;
import com.howard.www.business.service.IBusinessUserService;
import com.howard.www.business.service.IBussinessSecurityService;
import com.howard.www.common.message.apollo.client.HowardApolloServerClient;
import com.howard.www.common.message.apollo.client.entity.AudioManagerMsgEntity;
import com.howard.www.common.message.apollo.client.entity.OpenOtherAppEntity;
import com.howard.www.common.message.apollo.client.entity.ShutdownDeviceMsgEntity;
import com.howard.www.common.message.apollo.client.entity.SilentInstallMsgEntity;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("businessUserService")
public class BusinessUserServiceImpl implements IBusinessUserService {
	@Autowired
	private ApplicationContext cApplicationContext;

	public JSONObject userExitSystem(IDataTransferObject queryParameters) throws Exception {
		/**
		 * 
		 */
		return null;
	}

	public JSONObject obtainUserInfo(IDataTransferObject queryParameters) throws Exception {
		if (queryParameters == null) {
			throw new RuntimeException("用户名或密码不能为空");
		}
		String userName = FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("userName"));
		String userPassword = FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("userPassword"));
		if ("".equals(userName) || "".equals(userPassword)) {
			throw new RuntimeException("用户名或密码不能为空");
		}
		queryParameters.evaluteRequiredParameter("userPassword",
				obtainIBussinessSecurityService().obtainEncryptedString(userPassword));
		List<JSONObject> userInfo = obtainIBusinessUserDao().obtainUserInfo(queryParameters);
		if (userInfo == null || userInfo.size() != 1) {
			throw new RuntimeException("用户名或密码错误");
		}
		return JSONObject.fromObject(new LoginInfoEntity(userInfo.get(0)));
	}

	private IBussinessSecurityService obtainIBussinessSecurityService() throws Exception {
		return (IBussinessSecurityService) cApplicationContext.getBean("bussinessMD5SecurityService");
	}

	private IBusinessUserDao obtainIBusinessUserDao() throws Exception {
		return (IBusinessUserDao) cApplicationContext.getBean("businessUserDao");
	}

	private HowardApolloServerClient obtainHowardApolloServerClient() throws Exception {
		return (HowardApolloServerClient) cApplicationContext.getBean("howardApolloServerClient");
	}

	public JSONObject obtainUserInfoByUserId(IDataTransferObject queryParameters) throws Exception {
		if (queryParameters == null || ""
				.equals(FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("userId")))) {
			throw new RuntimeException("操作人员Id不能为空");
		}
		JSONObject userItem = obtainIBusinessUserDao().obtainUserInfoByUserId(queryParameters);
		if (userItem == null) {
			throw new RuntimeException("该操作人员在系统中不存在");
		}
		return userItem;
	}

	public JSONObject obtainSetDeviceSounds(IDataTransferObject queryParameters) throws Exception {
		if (queryParameters == null) {
			throw new RuntimeException("参数错误");
		}
		String soundSettingsEquipment = FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("taskValue"));
		String taskValue = FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("soundSettingsEquipment"));
		String taskKey = FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("taskKey"));
		if ("".equals(taskValue) || "".equals(taskKey) || "".equals(soundSettingsEquipment)) {
			throw new RuntimeException("参数错误");
		}
		AudioManagerMsgEntity audioManagerMsgEntity = new AudioManagerMsgEntity(queryParameters);
		obtainHowardApolloServerClient().sendSubjectMessage(
				FrameworkStringUtils.asString(JSONObject.fromObject(audioManagerMsgEntity)), "test/topic");
		return null;
	}

	@Override
	public JSONObject obtainSetSilentInstall(IDataTransferObject queryParameters) throws Exception {
		if (queryParameters == null) {
			throw new RuntimeException("参数错误");
		}
		String apkName = FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("apkName"));
		String apkNetworkAddress = FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("apkNetworkAddress"));
		String installEquipment = FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("installEquipment"));
		String packageName = FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("packageName"));
		if ("".equals(apkName) || "".equals(apkNetworkAddress) || "".equals(installEquipment)
				|| "".equals(packageName)) {
			throw new RuntimeException("参数错误");
		}
		SilentInstallMsgEntity silentInstallMsgEntity = new SilentInstallMsgEntity(queryParameters);

		obtainHowardApolloServerClient().sendSubjectMessage(
				FrameworkStringUtils.asString(JSONObject.fromObject(silentInstallMsgEntity)), "test/topic");
		return null;
	}

	@Override
	public JSONObject obtainSetShutDownDevice(IDataTransferObject queryParameters) throws Exception {
		if (queryParameters == null) {
			throw new RuntimeException("参数错误");
		}
		String closeEquipment = FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("closeEquipment"));
		String performTasksType = FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("performTasksType"));
		if("".equals(performTasksType)||"".equals(closeEquipment)){
			throw new RuntimeException("参数错误");
		}
		ShutdownDeviceMsgEntity shutdownDeviceMsgEntity = new ShutdownDeviceMsgEntity(queryParameters);
		obtainHowardApolloServerClient().sendSubjectMessage(
				FrameworkStringUtils.asString(JSONObject.fromObject(shutdownDeviceMsgEntity)), "test/topic");
		return null;
	}

	@Override
	public JSONArray obtainEquipmentItems(IDataTransferObject queryParameters) throws Exception {
		Vector<JSONObject> equipmentItems = new Vector<JSONObject>();
		equipmentItems.add(JSONObject.fromObject(new EquipmentRichselectEntity("8604520204", "HUAWEI G700-U00")));
		return JSONArray.fromObject(equipmentItems);
	}

	@Override
	public JSONObject obtainOpenOtherApp(IDataTransferObject queryParameters) throws Exception {
		if (queryParameters == null) {
			throw new RuntimeException("参数错误");
		}
		String switchingInterfaceEquipment= FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("switchingInterfaceEquipment"));
		String packageNameChange= FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("packageNameChange"));
		if("".equals(switchingInterfaceEquipment)||"".equals(packageNameChange)){
			throw new RuntimeException("参数错误");
		}
		// TODO Auto-generated method stub
		OpenOtherAppEntity openOtherAppEntity = new OpenOtherAppEntity(queryParameters);
		
		obtainHowardApolloServerClient().sendSubjectMessage(
				FrameworkStringUtils.asString(JSONObject.fromObject(openOtherAppEntity)), "test/topic");
		return null;
	}

}
