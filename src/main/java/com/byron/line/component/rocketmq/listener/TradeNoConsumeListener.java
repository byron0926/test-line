package com.byron.line.component.rocketmq.listener;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.ctx.SpringApplicationContext;
import com.byron.line.domain.Company;
import com.byron.line.req.TestReq;
import com.byron.line.service.CompanyService;
import lombok.Data;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @desc: 訂單状态更改消費者監聽中心
 *        主要處理玩家充值失效訂單
 * @author： byron
 * @createtime： 5/22/20183:38 PM
 * @modify by： ${user}
 * @modify time： 5/22/20183:38 PM
 * @desc of modify：
 * @throws:
 */
@Component
@Data
public class TradeNoConsumeListener implements MessageListenerConcurrently {
    private static final Logger log = LoggerFactory.getLogger(TradeNoConsumeListener.class);
    private CompanyService companyService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        log.info("----------訂單状态更改消費者消費消息--------------");
        list.stream().forEach(messageExt -> {
            log.info("----------處理消息----------");
            log.info("消息內容:{}",messageExt);
            try {
                String json = new String(messageExt.getBody());
                TestReq testReq = JSONObject.parseObject(json, TestReq.class);
                companyService = (CompanyService) SpringApplicationContext.getBean("TestService");
                companyService.insertCompany(new Company());
            } catch (Exception e){
                log.info("消費者處理消息拋出異常：{}",e);
            }
        });
        log.info("----------訂單状态更改消費者消費消息處理完成----------");
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
