package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @desc: 商户賬戶流水實體類
 * @author： kidy
 * @createtime： 5/26/20183:16 PM
 * @modify by： ${user}
 * @modify time： 5/26/20183:16 PM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyCashFlow implements Serializable {
    private static final long serialVersionUID = 209592652315176169L;

    private long id;
    private long companyId;
    private BigDecimal collectionAmount;
    private BigDecimal paymentAmount;
    private BigDecimal collectionBfs;
    private BigDecimal paymentBfs;
    private BigDecimal updatedAb;
    private BigDecimal oldAb;
    private BigDecimal updatedFa;
    private BigDecimal oldFa;
    private String orderNo;
    private int orderStatus;
}
