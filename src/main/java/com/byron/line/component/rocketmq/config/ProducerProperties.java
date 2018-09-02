package com.byron.line.component.rocketmq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @desc: 生產者配置 訂單更新
 * @author： byron
 * @createtime： 5/22/20183:09 PM
 * @modify by： ${user}
 * @modify time： 5/22/20183:09 PM
 * @desc of modify：
 * @throws:
 */
@Data
@Component
public class ProducerProperties {
    @Value("${rocketmq.producer.groupName}")
    private String groupName;
    @Value("${rocketmq.producer.instanceName}")
    private String instanceName;
    @Value("${rocketmq.producer.topic}")
    private String topic;
    @Value("${rocketmq.producer.topic2}")
    private String topic2;
    @Value("${rocketmq.producer.tag}")
    private String tag;
    @Value("${rocketmq.producer.tag2}")
    private String tag2;
    @Value("${rocketmq.producer.tag3}")
    private String tag3;
    @Value("${rocketmq.producer.tag4}")
    private String tag4;
    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize;
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout;
}
