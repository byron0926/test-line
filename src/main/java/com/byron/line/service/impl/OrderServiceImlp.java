package com.byron.line.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.util.*;
import com.byron.line.domain.Company;
import com.byron.line.domain.OrderDto;
import com.byron.line.mapper.OrderMapper;
import com.byron.line.service.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Service
public class OrderServiceImlp implements OrderService {


    private static final String HTTP_DOMAIN = "http://47.104.235.121:8092";
    /*抢单接口*/
    private static final String AsyResUrl = HTTP_DOMAIN + "";
    /*取消订单接口*/
    private static final String CANCEL_ORDER_HTTP_URL = HTTP_DOMAIN + "";


    @Autowired
    private OrderMapper orderMapper;

    private Logger logger = LoggerFactory.getLogger(OrderServiceImlp.class);


    @Override
    @Transactional
    public ResponseResult insertOrderAndReturnEntity(OrderDto orderDto) {
        /*初始化订单状态*/
        switch (orderDto.getChannelCode()) {
            case 1:
                orderDto.setQrcodeUrl("home/root/apliay" + new Random().nextInt(10) + ".jpg");
            case 2:
                orderDto.setQrcodeUrl("home/root/wechat" + new Random().nextInt(10) + ".jpg");
            case 3:
                orderDto.setQrcodeUrl("银行卡号");
        }
        String randomCode = this.checkRepeat();
        System.out.println("随机字符串=" + randomCode);
        /*查询该字符串是否重复*/
        if (orderMapper.queryOrderByRandomCode(randomCode) >= 1) {
            randomCode = RandomStringUtils.randomAlphanumeric(5);
        }
        orderDto.setRandomCode(randomCode);
        orderDto.setOrderNo(orderDto.getCompanyId() + "_" + DateUtil.getFormatDate(new Date(), DateUtils.YYYYMMDDHHMMSS));
        orderDto.setStatus(1);
        int orderId = orderMapper.insertOrder(orderDto);
        if (1 == orderId) {
            logger.info("回查订单orderId={}", orderDto.getId());
            OrderDto synOrderDto = orderMapper.queryOrderById(orderDto.getId());
            return ResponseResult.builder().code(200).msg("入单成功").object(synOrderDto).build();
        } else {
            return ResponseResult.builder().code(-9999).msg("入单失败").build();
        }
    }

    @Override
    @Transactional
    public ResponseResult updateOrder(OrderDto orderDto) {
        /*修改订单状态*/
        if (StringUtils.isEmpty(orderDto.getOrderNo())) {
            return ResponseResult.builder().code(-9999).msg("订单号为空").build();
        }
        if (StringUtils.isEmpty(orderDto.getAmount())) {
            return ResponseResult.builder().code(-9999).msg("订单金额为空").build();
        }
        orderDto.setStatus(2);
        int result = orderMapper.updateOrder(orderDto);
        ResponseResult rd = null;
        if (1 == result) {
            /*异步通知上分*/
            rd = ResponseResult.builder().code(200).msg("关单成功！").build();
//            try {
//                HttpClient.ResponseResult re = HttpClient.newInstance().sendHttpPost(orderDto.getNotifyUrl(), JSONObject.toJSONString(orderDto), null, null);
//                if (StringUtils.isEmpty(re)){
//                    throw new IllegalOptaionException(SystemCodeEnum.HTTP_NO_RESPONSE);
//                }
//                if (re.getHttpCode()==200){
//                    orderMapper.notify(orderDto.getOrderNo());
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        } else {
            rd = ResponseResult.builder().code(-9999).msg("随机码输入错误").build();
        }
        return rd;

//        String lock = redisDao.getKey(Constant.Lock.COMPANY_LOCK_PREFIX, orderDto.getCompanyId()+"");
//        boolean locked_company = true;
//        try {
//            if (redisDistributedLock.lock(lock)){
//                companyMapper.updCompany(orderDto);
//                logger.info("开始修改商户可用余额");
//            }else {
//                locked_company = false;
//                logger.info("该商户正在被锁,请等待");
//                throw new IllegalOptaionException(SystemCodeEnum.LOCK_TIMEOUT);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            /*釋放鎖*/
//            logger.info("釋放分布式锁");
//            redisDistributedLock.unlock(lock,locked_company);
//        }
//        return ResponseResult.builder().code(200).msg("该订单自动关单成功").build();
    }

    @Override
    public ResponseResult setOverTimeStatus(Long id) {
        orderMapper.setOverTimeStatus(id);
        return ResponseResult.builder().code(200).build() ;
    }

    private String checkRepeat(){
        String randomCode = RandomStringUtils.randomAlphanumeric(5);
        int result = orderMapper.queryOrderByRandomCode(randomCode);
        if(result>=1){
            return checkRepeat();
        }else {
            return randomCode;
        }
    }

    public static void main(String[] args) {

        OrderDto orderDto = OrderDto.builder()
                .notifyUrl("https://demo.html")
                .channelCode(1)
                .companyId(12l)
                .companyOrderNo("123456")
                .playerId("player12")
                .playerName("玩家1")
                .terminal(1)
                .build();
        String reqParam = JSONObject.toJSONString(orderDto);

        Company company = Company.builder()
                .companyName("AY")
                .email("12345@qq.com")
                .privateKey("qwertyuiop12345")
                .rate(new BigDecimal(0.05))
                .build();


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
        System.out.println("JSON字符串=" + reqParam);

    }


}
