package com.byron.line.component.rocketmq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @desc: 消費者配置 訂單更新
 * @author： byron
 * @createtime： 5/22/20183:09 PM
 * @modify by： ${user}
 * @modify time： 5/22/20183:09 PM
 * @desc of modify：
 * @throws:
 */
@Data
@Component
public class ConsumerProperties {
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.instanceName}")
    private String instanceName;
    @Value("${rocketmq.consumer.topic}")
    private String topic;
    @Value("${rocketmq.consumer.tag}")
    private String tag;
    @Value("${rocketmq.consumer.tag2}")
    private String tag2;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    @Value("${rocketmq.consumer.batchMaxSize}")
    private int batchMaxSize;
}
