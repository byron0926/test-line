package com.byron.line.controller;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.annotation.Validation;
import com.byron.line.common.annotation.VerifySign;
import com.byron.line.common.util.ResponseResult;
import com.byron.line.domain.OrderDto;
import com.byron.line.service.OrderService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
@RequestMapping(value = "/line",method = RequestMethod.POST)
public class AlipayCollectionController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AlipayCollectionController.class);

    @Autowired
    private OrderService orderService;

    /*
    * app接收到请求后抓取非空字段传给后台
    * 后台插入订单记录/并更新对应得可用余额，关单和自动上分。
    * */
//    @VerifySign()
    @RequestMapping("/deposit")
    @Validation
    public ResponseResult appReqLine(@RequestBody String param){
        logger.info("app请求充值入参->{}",param);
        OrderDto orderDto = JSONObject.parseObject(param,OrderDto.class);
        ResponseResult rr = null;
        try{
            orderService.updateOrder(orderDto);
            rr =  ResponseResult.builder().code(200).msg("订单入库完成").build();
        }catch (Exception e){
            e.printStackTrace();
            exception(e,"/line/deposit");
        }
        return rr;
    }

}
