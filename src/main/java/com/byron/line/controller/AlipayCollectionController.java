package com.byron.line.controller;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.util.RSAUtil;
import com.byron.line.common.util.ResponseResult;
import com.byron.line.common.util.md5.Digest;
import com.byron.line.constant.Constant;
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

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.byron.line.common.util.md5.Digest.digest;

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
    public ResponseResult appReqLine(@RequestBody String param){
        logger.info("app请求充值入参->{}",param);
        /*验签*/
        if(!this.verifySign(param)){
            return ResponseResult.builder().code(-9999).msg("验签失败").build();
        }
        OrderDto orderDto = JSONObject.parseObject(param,OrderDto.class);
        logger.info("订单实体={}",orderDto);
        ResponseResult rr = null;
        try{
            rr = orderService.updateOrder(orderDto);
        }catch (Exception e){
            e.printStackTrace();
            exception(e,"/line/deposit");
        }
        logger.info("同步响应给app的消息={}",rr);
        return rr;
    }

    private boolean verifySign(String param){
        OrderDto orderDto = JSONObject.parseObject(param,OrderDto.class);
        log.info("映射实体类orderDto={}",orderDto);
        Map<String,String> map = new HashMap<>();
        map.put("companyId",String.valueOf(orderDto.getCompanyId()));
        map.put("amount",String.valueOf(orderDto.getAmount()));
        map.put("playerName",orderDto.getPlayerName());
        map.put("orderNo",orderDto.getOrderNo());
        map.put("orderCrtTime",orderDto.getOrderCrtTime());
        map.put("playerAccount",orderDto.getPlayerAccount());
        map.put("type",String.valueOf(orderDto.getType()));
        String sign = orderDto.getSign();
        PublicKey pk = null;
        boolean flag = false;
        try {
            List<String> list = new ArrayList<>();
            logger.info("map类型参数：{}", map);
            String tesmp = Digest.mapToString(Digest.treeMap(map, list));
            logger.info("MD5之前的字符串={}", tesmp);
            String MD5 = digest(tesmp);
            logger.info("MD5={}", MD5);
            pk = RSAUtil.getPublicKey(Constant.RSASign.PUBLIC_KEY);
            flag = RSAUtil.vertiy(MD5,sign,pk);
            logger.info("验签结果={}",flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
