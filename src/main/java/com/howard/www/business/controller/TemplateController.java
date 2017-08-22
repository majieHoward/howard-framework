package com.howard.www.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
/**
 * @Controller @Scope("prototype")
 **/
@Controller
public class TemplateController {
	protected final Logger log = LoggerFactory.getLogger(TemplateController.class);

	/**
	 * @Autowired private StringRedisTemplate stringRedisTemplate;
	 * @Autowired private RedisTemplate<String, Object> redisTemplate;
	 * @Autowired @Qualifier("businessInitparametersService") private
	 *            IBusinessInitparametersService businessInitparametersService;
	 * @Autowired @Qualifier("obtainUnprocessedOrderService") private
	 *            IObtainUnprocessedOrderService
	 *            obtainUnprocessedOrderService; @RequestMapping("/helloHtml")
	 *            public String helloHtml(Map<String, Object> map) {
	 *            IDataTransferObject queryParameters = this.getParamOfDto();
	 *            log.info(queryParameters.obtainMapOfRequiredParameter().get("a")
	 *            + ""); stringRedisTemplate.opsForValue().set("leader",
	 *            "howard"); LoginInfoEntity howardLoginInfoEntity = new
	 *            LoginInfoEntity("howard", "100");
	 *            redisTemplate.opsForValue().set("howard",
	 *            howardLoginInfoEntity); try { map.put("hello",
	 *            JSONObject.fromObject(obtainUnprocessedOrderService.obtainOneOfUnprocessedOrderDetails(queryParameters)));
	 *            } catch (Exception e1) { // TODO Auto-generated catch block
	 *            e1.printStackTrace(); } return "/helloHtml"; }
	 **/
}
