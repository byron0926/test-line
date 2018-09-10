package com.byron.line.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.byron.line.common.util.DateUtil;
import com.byron.line.common.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    @JSONField
    private Long companyId;//商户唯一id
    private Integer channelCode;//1-支付宝/2-微信/3-银行卡
    private String playerName;//玩家姓名
    private String orderNo;//交易订单号
    private Integer type;//  收款理由：1-转帐和2-收款
    private String playerAccount;//玩家账户
    private BigDecimal amount;
    private String orderCrtTime;//订单交易时间
    private String reqTime;


    private String playerId;//玩家在商户平台得id
    private Integer terminal;//终端PC 1/安卓 2
    private String qrcodeUrl;//同步响应给商户得
    private String companyOrderNo;//商户订单号

    private String randomCode;//随机码
    private String note;
    private Integer status;
    private String crtTime;//订单插入时间
    private String notifyUrl;
    private String sign;

    public void setReqTime(String reqTime) {
        this.reqTime = DateUtil.getFormatDate(new Date(),DateUtils.YYYY_MM_DD_HH_MM_SS);
    }

    public String getReqTime() {
        return DateUtil.getFormatDate(new Date(),DateUtils.YYYY_MM_DD_HH_MM_SS);
    }
}
