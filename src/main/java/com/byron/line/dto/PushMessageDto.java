package com.byron.line.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: 推送消息实体类
 * @author： kidy
 * @createtime： 5/31/20182:17 PM
 * @modify by： ${user}
 * @modify time： 5/31/20182:17 PM
 * @desc of modify：
 * @throws:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushMessageDto implements Serializable {
    private static final long serialVersionUID = 8063799965275772733L;
    /*1 玩家充值 2 玩家提现恭喜什么鬼 3 玩家提现打入前十名 4 失效订单*/
    private int tradeType;
    /*推送消息*/
    private Object message;
    /*订单号*/
    private String orderNo;
    /* -1 不处理 */
    private long playerId;
}
