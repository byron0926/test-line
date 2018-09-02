package com.byron.line.component.redis.listener;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.exception.IllegalOptaionException;
import com.byron.line.common.util.StringUtils;
import com.byron.line.component.redis.api.RedisDao;
import com.byron.line.component.redis.lock.RedisDistributedLock;
import com.byron.line.component.rocketmq.base.BaseMessage;
import com.byron.line.component.rocketmq.config.RocketMQProperties;
import com.byron.line.constant.Constant;
import com.byron.line.res.TestRes;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @desc: Redis鍵值失效監聽中心
 * @author： byron
 * @createtime： 5/22/201812:55 PM
 * @modify by： ${user}
 * @modify time： 5/22/201812:55 PM
 * @desc of modify：
 * @throws:
 */
@Component
@Deprecated
public class RedisExpiredListener extends BaseMessage implements MessageListener {
    private static final Logger log = LoggerFactory.getLogger(RedisExpiredListener.class);
    public final static String LISTENER_PATTERN = "__key*__:*";
    @Autowired
    private RocketMQProperties properties;
    @Resource
    private TransactionMQProducer transactionMQProducer;
    @Resource
    private RedisDao redisDao;
    @Resource
    private RedisDistributedLock redisDistributedLock;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String content = new String(body);
        TestRes tradeStatusDto = TestRes.builder()
                .id(2l)
                .status(0)
                .build();
        log.info(String.format("channel: %s, body: %s, bytes: %s"
                ,new String(channel), content, new String(bytes)));
        //過濾消息 僅處理玩家充值訂單號
        if (content.startsWith(Constant.RedisPrefix.TRADE_ORDER_NO_PREFIX)){
            String code = content.substring(Constant.RedisPrefix.TRADE_ORDER_NO_PREFIX.length()+1);
            try {
                /*添加分佈式鎖*/
                if (redisDistributedLock.lock()){
                    if (isExistMessage(code)){
                        //推送超時失效訂單消息至中間件
                        log.info("--------------推送超時失效訂單消息至中間件-------------");
                        sendMessage(code, JSONObject.toJSONString(tradeStatusDto).getBytes(),transactionMQProducer,properties.getProducerProperties().getTopic(),properties.getProducerProperties().getTag());
                    } else {
                        //消息已被其他節點推送，無需重複推送
                        log.info("消息[{}]已被其他節點推送，無需重複推送",content);
                    }
                }
            } catch (IllegalOptaionException e) {
                log.error("redis超時失效監聽出現異常：{}",e);
            } finally {
                /*釋放鎖*/
                redisDistributedLock.unlock();
            }
        }
    }

    /**
     * 判斷redis臨時緩存中是否已存在發送的訂單號
     * @param code
     * @return
     */
    private boolean isExistMessage(String code){
        String value = redisDao.get(redisDao.getKey(Constant.RedisPrefix.TEMPORARY_TRADE_ORDER_NO_PREFIX,code));
        log.info("value:={}",value);
        if (StringUtils.isEmpty(value)){
            /*首次被推送，緩存至臨時保存內存中，返回true*/
            redisDao.setString(Constant.RedisPrefix.TEMPORARY_TRADE_ORDER_NO_PREFIX,code,code,Constant.RedisPrefix.TEMPORARY_TRADE_ORDER_NO_TIMEOUT);
            return true;
        } else {
            return false;
        }
    }
}
