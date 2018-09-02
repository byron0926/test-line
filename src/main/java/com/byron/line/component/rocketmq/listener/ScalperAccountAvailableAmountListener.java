package com.byron.line.component.rocketmq.listener;

import com.alibaba.fastjson.JSON;
import com.byron.line.common.ctx.SpringApplicationContext;
import com.byron.line.req.TestReq;
import com.byron.line.service.TestService;
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
 * @desc: 黃牛提現消費者監聽中心
 * @author： byron
 * @createtime： 5/26/20183:04 PM
 * @modify by： ${user}
 * @modify time： 5/26/20183:04 PM
 * @desc of modify：
 * @throws:
 */
@Component
@Data
public class ScalperAccountAvailableAmountListener implements MessageListenerConcurrently {
    private static final Logger log = LoggerFactory.getLogger(ScalperAccountAvailableAmountListener.class);
    private TestService testService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        log.info("----------黃牛提現消費者消費消息--------------");
        list.stream().forEach(messageExt -> {
            log.info("----------處理消息----------");
            log.info("消息內容:{}",messageExt);
            try {
                String json = messageExt.getProperties().get("KEYS");
                TestReq testReq = JSON.parseObject(json, TestReq.class);
                log.info("測試使用看消息:{}",testReq);
                testService = (TestService) SpringApplicationContext.getBean("TestService");
                testService.test(testReq);
            } catch (Exception e){
                log.info("消費者處理消息拋出異常：{}",e);
            }
        });
        log.info("----------黃牛提現消費者消費消息處理完成----------");
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
