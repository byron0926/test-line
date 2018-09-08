package com.byron.line.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.util.*;
import com.byron.line.component.redis.api.RedisDao;
import com.byron.line.component.redis.lock.RedisDistributedLock;
import com.byron.line.domain.OrderDto;
import com.byron.line.mapper.CompanyMapper;
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



    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CompanyMapper companyMapper;

//    @Autowired
//    private RedisDao redisDao;
//    @Autowired
//    private RedisDistributedLock redisDistributedLock;

    private Logger logger = LoggerFactory.getLogger(OrderServiceImlp.class);


    @Override
    @Transactional
    public ResponseResult insertOrderAndReturnEntity(OrderDto orderDto) {
        /*初始化订单状态*/
        switch (orderDto.getChannelCode()){
            case 1:orderDto.setQrcodeUrl("home/root/apliay"+new Random().nextInt(10) +".jpg");
            case 2:orderDto.setQrcodeUrl("home/root/wechat"+new Random().nextInt(10) +".jpg");
            case 3:orderDto.setQrcodeUrl("银行卡号");
        }
        String randomCode = RandomStringUtils.randomAlphanumeric(5);
        System.out.println("随机字符串="+randomCode);
        /*查询该字符串是否重复*/
        if(orderMapper.queryOrderByRandomCode(randomCode)>=1){
            randomCode = RandomStringUtils.randomAlphanumeric(5);
        }
        orderDto.setRandomCode(randomCode);
        orderDto.setOrderNo(orderDto.getCompanyId()+"_"+DateUtil.getFormatDate(new Date(),DateUtils.YYYYMMDDHHMMSS));
        orderDto.setStatus(1);
        int orderId = orderMapper.insertOrder(orderDto);
        if(1 == orderId){
            logger.info("回查订单orderId={}",orderDto.getId());
            OrderDto synOrderDto = orderMapper.queryOrderById(orderDto.getId());
            return ResponseResult.builder().code(200).msg("入单成功").object(synOrderDto).build();
        }else {
            return ResponseResult.builder().code(-9999).msg("入单失败").build();
        }


    }

    @Override
    @Transactional
    public ResponseResult updateOrder(OrderDto orderDto) {
        /*修改订单状态*/
        if(StringUtils.isEmpty(orderDto.getOrderNo())){
            return ResponseResult.builder().code(-9999).msg("订单号为空").build();
        }
        if(StringUtils.isEmpty(orderDto.getAmount())){
            return ResponseResult.builder().code(-9999).msg("订单金额为空").build();
        }
        orderDto.setStatus(2);
        int result = orderMapper.updateOrder(orderDto);
        if(1 == result){
            /*异步通知上分*/
            try {
                HttpClient.newInstance().sendHttpPost("",JSONObject.toJSONString(orderDto),null,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResponseResult.builder().code(200).msg("关单成功！").object(orderDto).build();
        }else {
            return ResponseResult.builder().code(-9999).msg("订单号不存在").build();
        }

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

    public static void main(String[] args){

        OrderDto orderDto = OrderDto.builder()
                .channelCode(1)
                .companyId(12l)
                .companyOrderNo("123456")
                .playerId("player12")
                .playerName("玩家1")
                .terminal(1)
                .build();
        String reqParam = JSONObject.toJSONString(orderDto);
        OrderDto orderDtoApp = OrderDto.builder()
                .amount(new BigDecimal(100))
                .orderCrtTime("2018-05-12 10:12:24")
                .orderNo("12_20180908151411")
                .note("QWER")
                .type(1)
                .build();
        String orderDtoAppStr = JSONObject.toJSONString(orderDtoApp);
        System.out.println("app请求访问参数="+orderDtoAppStr);
        System.out.println("JSON字符串="+reqParam);

    }


}
