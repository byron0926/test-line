package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long companyId;
    private Integer channelCode;
    private String playerId;
    private String companyOrderNo;
    private String playerName;
    private String cardNo;
    private String terminal;//终端PC 1/安卓 2
    private String qrcodeUrl;
    private String orderNo;
    private Integer type;//1-支付宝/2-微信/3-银行卡
    private String randomCode;//随机码


}
