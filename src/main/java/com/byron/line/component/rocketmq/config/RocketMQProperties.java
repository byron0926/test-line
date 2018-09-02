package com.byron.line.component.rocketmq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @desc: rocketmq配置信息
 * @author： byron
 * @createtime： 5/22/20182:55 PM
 * @modify by： ${user}
 * @modify time： 5/22/20182:55 PM
 * @desc of modify：
 * @throws:
 */
@Data
@Component
public class RocketMQProperties {
    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;
    @Resource
    private ProducerProperties producerProperties;
    @Resource
    private ConsumerProperties consumerProperties;
}
