package com.howard.www.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import net.sf.json.JSONObject;
/**
 * 
 * @ClassName:  ExternalProvidedHisController   
 * @Description:TODO(向Hospital Information System 提供的接口)   
 * @author: mayijie
 * @date:   2017年8月21日 下午4:40:29   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@RestController
public class ExternalProvidedHisController {
	
	protected final Logger log = LoggerFactory.getLogger(ExternalProvidedHisController.class);
	@Autowired
	private ApplicationContext cApplicationContext;
    /**
     * 
     * @Title: doctorOnlineStatus   
     * @Description: TODO HIS传递医生登入登出状态的接口
     * @param: @param requiredParameter
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
	@RequestMapping("/developing/his/transfer/hospital.doctorOnlineStatus.interfaces")
	public String doctorOnlineStatus(IDataTransferObject requiredParameter) throws Exception {
		return FrameworkStringUtils.asString(JSONObject.fromObject(requiredParameter));
	}
	
	/**
	 * 
	 * @Title: callThePatient   
	 * @Description: TODO HIS呼叫待诊者就诊的接口  
	 * @param: @param requiredParameter
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/developing/his/transfer/hospital.callThePatient.interfaces")
	public String callThePatient(IDataTransferObject requiredParameter) throws Exception {
		return FrameworkStringUtils.asString(JSONObject.fromObject(requiredParameter));
	}
}
