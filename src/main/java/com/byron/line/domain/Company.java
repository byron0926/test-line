package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @desc:
 * @author： kidy
 * @createtime： 5/30/201810:03 AM
 * @modify by： ${user}
 * @modify time： 5/30/201810:03 AM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company implements Serializable {
    private static final long serialVersionUID = 1134548551089358513L;

    private long id;
    private String address;
    private String charge;
    private String fixPhone;
    private String companyName;
    private String companyCode;
    private BigDecimal freezingAmount;
    private BigDecimal availableAmount;
    private BigDecimal totalAmount;
    private String email;
    private String website;
    private int status;
    private String createTime;
    private String updateTime;
    private String note;
    private int ifRate;
}
