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

    private Long id;
    private String companyName;
    private String email;
    private String privateKey;
    private BigDecimal totalAmount;
    private BigDecimal availableAmount;
    private BigDecimal freezingAmount;
    private String crtTime;
    private String updTime;
    private BigDecimal rate;


}
