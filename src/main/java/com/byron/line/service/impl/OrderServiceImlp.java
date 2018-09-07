package com.byron.line.service.impl;

import com.byron.line.common.enums.SystemCodeEnum;
import com.byron.line.common.exception.IllegalOptaionException;
import com.byron.line.common.util.ResponseResult;
import com.byron.line.common.util.StringUtils;
import com.byron.line.component.redis.api.RedisDao;
import com.byron.line.component.redis.lock.RedisDistributedLock;
import com.byron.line.constant.Constant;
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

import java.util.Random;

@Service
public class OrderServiceImlp implements OrderService {



    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private RedisDistributedLock redisDistributedLock;

    private Logger logger = LoggerFactory.getLogger(OrderServiceImlp.class);


    @Override
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
        Long orderId = orderMapper.insertOrder(orderDto);
        OrderDto synOrderDto = orderMapper.queryOrderById(orderId);
        return ResponseResult.builder().code(200).msg("入单成功").object(synOrderDto).build();

    }

    @Override
    @Transactional
    public ResponseResult updateOrder(OrderDto orderDto) {
        /*修改订单状态*/
        if(StringUtils.isEmpty(orderDto.getOrderNo())){
            return ResponseResult.builder().code(-9999).msg("订单号为空").build();
        }
        orderDto.setStatus(2);
        int result = orderMapper.updateOrder(orderDto);
        if(1 == result){
            return ResponseResult.builder().code(200).msg("关单成功！").build();
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


}
