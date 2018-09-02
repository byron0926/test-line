package com.byron.line.domain;

import com.byron.line.common.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @desc: 黃牛交易信息
 * @author： kidy
 * @createtime： 5/23/20188:00 PM
 * @modify by： ${user}
 * @modify time： 5/23/20188:00 PM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trade implements Serializable {
    private static final long serialVersionUID = -2342527358748070168L;

    private long id;
    private long scalperId;
    private long scalperInfoId;
    private BigDecimal rateFee;
    private long playerId;
    private String tradeNo;
    private int tradeType;
    private BigDecimal balane;
    private int status;
    private String pictureUrl;
    private String uploadTime;
    private String trueTime;
    private BigDecimal trueBalance;
    private String remark;
    private BigDecimal earning;
    private String tradeSeriesNo;
    private String tradeChannel;
    private String createTime;
    private String disableTime;
    private String updateTime;
    private BigDecimal scalperTotalAmount;
    private String now;

    public String getNow() {
        return DateUtils.dateToString(new Date(),DateUtils.YYYY_MM_DD_HH_MM_SS);
    }

    public void setNow(String now) {
        this.now = now;
    }
}
