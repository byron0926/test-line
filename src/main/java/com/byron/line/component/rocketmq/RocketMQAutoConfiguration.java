package com.byron.line.component.rocketmq;

import com.byron.line.component.rocketmq.config.RocketMQProperties;
import com.byron.line.component.rocketmq.listener.ScalperAccountAvailableAmountListener;
import com.byron.line.component.rocketmq.listener.TradeNoConsumeListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @desc: Rocketmq消息中間件生產者與消費者配置
 * @author： byron
 * @createtime： 5/22/20182:54 PM
 * @modify by： ${user}
 * @modify time： 5/22/20182:54 PM
 * @desc of modify：
 * @throws:
 */
@Configuration
public class RocketMQAutoConfiguration {
    @Autowired
    private RocketMQProperties properties;
    @Resource
    private TradeNoConsumeListener tradeNoConsumeListener;
    @Resource
    private ScalperAccountAvailableAmountListener scalperAccountAvailableAmountListener;

    /**
     * 初始化事物消息生產者
     * @return
     * @throws MQClientException
     */
    @Bean
    public TransactionMQProducer transactionMQProducer() throws MQClientException {
        TransactionMQProducer producer =
                new TransactionMQProducer(properties.getProducerProperties().getGroupName());
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.setInstanceName(properties.getProducerProperties().getInstanceName());
        producer.setMaxMessageSize(properties.getProducerProperties().getMaxMessageSize());
        producer.setSendMsgTimeout(properties.getProducerProperties().getSendMsgTimeout());
        producer.start();
        return producer;
    }

    @Bean("transactionMQProducer2")
    public TransactionMQProducer transactionMQProducer2() throws MQClientException {
        TransactionMQProducer producer =
                new TransactionMQProducer(properties.getProducerProperties().getGroupName()+1);
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.setInstanceName(properties.getProducerProperties().getInstanceName()+1);
        producer.setMaxMessageSize(properties.getProducerProperties().getMaxMessageSize());
        producer.setSendMsgTimeout(properties.getProducerProperties().getSendMsgTimeout());
        producer.start();
        return producer;
    }

    /**
     * 超時訂單消費者
     * @return
     * @throws MQClientException
     */
    @Bean
    public DefaultMQPushConsumer pushConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer =
                new DefaultMQPushConsumer(properties.getConsumerProperties().getGroupName());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr(properties.getNamesrvAddr());
        consumer.setInstanceName(properties.getConsumerProperties().getInstanceName());
        consumer.setConsumeThreadMin(properties.getConsumerProperties().getConsumeThreadMin());
        consumer.setConsumeThreadMax(properties.getConsumerProperties().getConsumeThreadMax());
        consumer.setConsumeMessageBatchMaxSize(properties.getConsumerProperties().getBatchMaxSize());
        consumer.subscribe(properties.getConsumerProperties().getTopic(),
                properties.getConsumerProperties().getTag());
        consumer.registerMessageListener(tradeNoConsumeListener);
        consumer.start();
        return consumer;
    }

    /**
     * 黃牛提現消費者
     * @return
     * @throws MQClientException
     */
    @Bean
    public DefaultMQPushConsumer pushConsumer2() throws MQClientException {
        DefaultMQPushConsumer consumer =
                new DefaultMQPushConsumer(properties.getConsumerProperties().getGroupName()+2);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr(properties.getNamesrvAddr());
        consumer.setInstanceName(properties.getConsumerProperties().getInstanceName()+2);
        consumer.setConsumeThreadMin(properties.getConsumerProperties().getConsumeThreadMin());
        consumer.setConsumeThreadMax(properties.getConsumerProperties().getConsumeThreadMax());
        consumer.setConsumeMessageBatchMaxSize(properties.getConsumerProperties().getBatchMaxSize());
        consumer.subscribe(properties.getConsumerProperties().getTopic(),
                properties.getConsumerProperties().getTag2());
        consumer.registerMessageListener(scalperAccountAvailableAmountListener);
        consumer.start();
        return consumer;
    }

}
