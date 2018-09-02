package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @desc: 黃牛日收益
 * @author： kidy
 * @createtime： 5/26/20185:25 PM
 * @modify by： ${user}
 * @modify time： 5/26/20185:25 PM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScalperDaily implements Serializable {
    private static final long serialVersionUID = 8375282266586859286L;

    private long id;
    private long scalperId;
    private String account;
    private BigDecimal transactionAmountDaily;
    private BigDecimal earningDaily;
    private BigDecimal frequencyDaily;
    private BigDecimal collectionDaily;
    private BigDecimal earningCollectionDaily;
    private BigDecimal frequencyCollectionDaily;
    private BigDecimal paymentDaily;
    private BigDecimal earningPaymentDaily;
    private BigDecimal frequencyPaymentDaily;
    private String createTime;
    private String updateTIme;
}
