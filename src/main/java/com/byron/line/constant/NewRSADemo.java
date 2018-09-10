package com.byron.line.constant;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.util.RSACoderUtils;
import com.byron.line.domain.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class NewRSADemo {

    private static Logger logger = LoggerFactory.getLogger(TestDemo.class);
    public static  void main(String[] args) {
        OrderDto orderDtoApp = OrderDto.builder()
                .amount(new BigDecimal(100))
                .playerAccount("12345@qq.com")
                .orderCrtTime("2018-05-12 10:12:24")
                .orderNo("12_20180908151411")
                .note("QWERt")
                .type(1)
                .build();
        String orderDtoAppStr = JSONObject.toJSONString(orderDtoApp);
        System.out.println("app请求访问参数=" + orderDtoAppStr);
        String sign = null;
        boolean flag = false;
        try {
            /*签名生成*/
            sign = RSACoderUtils.signString(orderDtoAppStr,Constant.RSASign.PRIVATE_KEY);
            /*解签*/
            flag = RSACoderUtils.verify(orderDtoAppStr,Constant.RSASign.PUBLIC_KEY,sign);
            logger.info("签名结果={}",flag);
            logger.info("sign={}",sign);
            orderDtoApp.setSign(sign);
            String json = JSONObject.toJSONString(orderDtoApp);
            logger.info("json={}",json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
