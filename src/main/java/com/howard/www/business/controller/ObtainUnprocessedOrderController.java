package com.howard.www.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import com.howard.www.business.domain.BackInteractivenfoEntity;
import com.howard.www.business.service.IBusinessUserService;
import com.howard.www.business.service.IObtainUnprocessedOrderService;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.base.web.controller.BaseController;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: ObtainUnprocessedOrderController
 * @Description:TODO 获取未处理的订单先从回退订单中查找然后在原始订单中查找
 * @author: mayijie
 * @date: 2017年2月17日 上午11:36:23
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
/**
 * @RestController
 *
**/
public class ObtainUnprocessedOrderController extends BaseController {
	protected final Logger log = LoggerFactory.getLogger(ObtainUnprocessedOrderController.class);
	@Autowired
	private ApplicationContext cApplicationContext;

	/**
	 * 
	 * @Title: scanUnprocessedOrder @Description: TODO
	 *         扫描是否有订单 @param: @return @param: @throws Exception @return:
	 *         String @throws
	 */
	@RequestMapping("/order/scanUnprocessedOrder.howard")
	public String scanUnprocessedOrder(HttpServletRequest request) throws Exception {
		JSONObject orderItem = obtainIObtainUnprocessedOrderService()
				.obtainOneOfUnprocessedOrderDetails(this.getParamOfDto());
		BackInteractivenfoEntity backInteractivenfoEntity = new BackInteractivenfoEntity();
		backInteractivenfoEntity.setInteractiveData(orderItem);
		backInteractivenfoEntity.setRequestAddress(FrameworkStringUtils.asString(request.getRequestURL().toString()));
		return FrameworkStringUtils.asString(JSONObject.fromObject(backInteractivenfoEntity));
	}

	@RequestMapping("/order/equipmentRichselect.howard")
	public String equipmentRichselect(HttpServletRequest request) throws Exception {
		return FrameworkStringUtils.asString(obtainIBusinessUserService().obtainEquipmentItems(this.getParamOfDto()));
	}

	@RequestMapping("/order/sendThemeMessage.howard")
	public String sendThemeMessage(HttpServletRequest request) throws Exception {
		obtainIBusinessUserService().obtainSetDeviceSounds(this.getParamOfDto());
		return FrameworkStringUtils.asString(JSONObject.fromObject("{'success':'success'}"));
	}

	@RequestMapping("/order/sendShutDownMessage.howard")
	public String sendShutDownMessage(HttpServletRequest request) throws Exception {
		obtainIBusinessUserService().obtainSetShutDownDevice(this.getParamOfDto());
		return FrameworkStringUtils.asString(JSONObject.fromObject("{'success':'success'}"));
	}

	@RequestMapping("/order/sendSilentInstallMessage.howard")
	public String sendSilentInstallMessage(HttpServletRequest request) throws Exception {
		obtainIBusinessUserService().obtainSetSilentInstall(this.getParamOfDto());
		return FrameworkStringUtils.asString(JSONObject.fromObject("{'success':'success'}"));
	}
	@RequestMapping("/order/sendOpenOtherAppMessage.howard")
	public String sendOpenOtherAppMessage()throws Exception{
		obtainIBusinessUserService().obtainOpenOtherApp(this.getParamOfDto());
		return FrameworkStringUtils.asString(JSONObject.fromObject("{'success':'success'}"));
	}
	@RequestMapping("/order/completedObtainOrder.howard")
	public String completedObtainOrder(HttpServletRequest request) throws Exception {
		BackInteractivenfoEntity backInteractivenfoEntity = new BackInteractivenfoEntity();
		backInteractivenfoEntity.setInteractiveData(
				obtainIObtainUnprocessedOrderService().evaluateCompletedObtainOrder(this.getParamOfDto()));
		backInteractivenfoEntity.setRequestAddress(FrameworkStringUtils.asString(request.getRequestURL().toString()));
		return FrameworkStringUtils.asString(JSONObject.fromObject(backInteractivenfoEntity));
	}

	@RequestMapping("/order/totalNumberOfOrders.howard")
	public String totalNumberOfOrdersCompleted(HttpServletRequest request) throws Exception {
		BackInteractivenfoEntity backInteractivenfoEntity = new BackInteractivenfoEntity();
		backInteractivenfoEntity.setInteractiveData(
				obtainIObtainUnprocessedOrderService().obtainTotalNumberOfOrdersCompleted(this.getParamOfDto()));
		backInteractivenfoEntity.setRequestAddress(FrameworkStringUtils.asString(request.getRequestURL().toString()));
		return FrameworkStringUtils.asString(JSONObject.fromObject(backInteractivenfoEntity));
	}

	@RequestMapping("/system/login.howard")
	public String systemLogin(HttpServletRequest request) throws Exception {
		BackInteractivenfoEntity backInteractivenfoEntity = new BackInteractivenfoEntity();
		backInteractivenfoEntity.setInteractiveData(obtainIBusinessUserService().obtainUserInfo(this.getParamOfDto()));
		backInteractivenfoEntity.setRequestAddress(FrameworkStringUtils.asString(request.getRequestURL().toString()));
		return FrameworkStringUtils.asString(JSONObject.fromObject(backInteractivenfoEntity));
	}

	@RequestMapping("/system/loginOut.howard")
	public String systemLoginOut() throws Exception {

		return null;
	}

	@RequestMapping("/system/buriedPoint.howard")
	public void buriedPoint() throws Exception {
		IDataTransferObject dto = this.getParamOfDto();
		log.info(FrameworkStringUtils.asString(JSONObject.fromObject(dto)));
	}

	private IBusinessUserService obtainIBusinessUserService() throws Exception {
		return (IBusinessUserService) cApplicationContext.getBean("businessUserService");
	}

	private IObtainUnprocessedOrderService obtainIObtainUnprocessedOrderService() throws Exception {
		return (IObtainUnprocessedOrderService) cApplicationContext.getBean("obtainUnprocessedOrderService");
	}
}
