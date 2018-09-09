package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private Long companyId;//商户唯一id
    private Integer channelCode;//1-支付宝/2-微信/3-银行卡
    private String playerName;//玩家姓名
    private String orderNo;//交易订单号
    private Integer type;//  收款理由：1-转帐和2-收款
    private String playerAccount;//玩家账户
    private BigDecimal amount;
    private String orderCrtTime;

    private String playerId;//玩家在商户平台得id
    private Integer terminal;//终端PC 1/安卓 2
    private String qrcodeUrl;//同步响应给商户得
    private String companyOrderNo;//商户订单号

    private String randomCode;//随机码
    private String note;
    private Integer status;
    private String crtTime;//订单创建时间
    private String notifyUrl;
}
