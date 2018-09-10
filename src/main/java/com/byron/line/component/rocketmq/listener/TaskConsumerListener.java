package com.byron.line.component.rocketmq.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.ctx.SpringApplicationContext;
import com.byron.line.domain.Company;
import com.byron.line.domain.OrderDto;
import com.byron.line.mapper.TaskFlowMapper;
import com.byron.line.req.TestReq;
import com.byron.line.service.CompanyService;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskConsumerListener implements MessageListenerConcurrently {
    private Logger logger = LoggerFactory.getLogger(TaskConsumerListener.class);
    @Autowired
    private TaskFlowMapper taskFlowMapper;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

        logger.info("app请求异步处理插入请求参数字段");
        list.stream().forEach(messageExt->{
            logger.info("-------消息处理-------");
            logger.info("消息内容={}",messageExt);
            try {
                String json = new String(messageExt.getBody());
                OrderDto orderDto = JSONObject.parseObject(json,OrderDto.class);
                taskFlowMapper.insertTaskFlow(orderDto);
            } catch (Exception e) {
                logger.info("消費者處理消息拋出異常：{}",e);
            }
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
