package com.byron.line.component.rocketmq.base;

import com.byron.line.common.util.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc: 基礎消息
 * @author： byron
 * @createtime： 5/26/20184:12 PM
 * @modify by： ${user}
 * @modify time： 5/26/20184:12 PM
 * @desc of modify：
 * @throws:
 */
public class BaseMessage {
    private static final Logger log = LoggerFactory.getLogger(BaseMessage.class);

    public boolean sendMessage(String content,byte[] body,
                            TransactionMQProducer transactionMQProducer,
                            String topic,String tag){
        org.apache.rocketmq.common.message.Message msg =
                new org.apache.rocketmq.common.message.Message(
                        topic,
                        tag,
                        content,
                        body);
        msg.setDelayTimeLevel(1);
        try {
            SendResult sendResult =  transactionMQProducer.send(msg);
            log.info("推送結果:{}",sendResult);
            if (StringUtils.isNotEmpty(sendResult) && "SEND_OK".equals(sendResult.getSendStatus().name())){
                log.info("推送完成!");
                return true;
            } else {
                log.info("推送失敗，請手動處理這條超時訂單數據[{}]!",content);
            }
        } catch (MQClientException e) {
            log.error("MQClientException:{}",e);
        } catch (RemotingException e) {
            log.error("RemotingException:{}",e);
        } catch (MQBrokerException e) {
            log.error("MQBrokerException:{}",e);
        } catch (InterruptedException e) {
            log.error("InterruptedException:{}",e);
        } catch (Exception e) {
            log.error("消息提供者拋出異常信息:{}", e);
        }
        return false;
    }
}

