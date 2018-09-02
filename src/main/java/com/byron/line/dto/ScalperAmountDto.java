package com.byron.line.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @desc: 黃牛賬戶金額信息
 * @author： kidy
 * @createtime： 5/28/20185:44 PM
 * @modify by： ${user}
 * @modify time： 5/28/20185:44 PM
 * @desc of modify：
 * @throws:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScalperAmountDto implements Serializable {
    private static final long serialVersionUID = -2495856011736845343L;
    private BigDecimal totalAmount;
    private BigDecimal freezingAmount;
    private BigDecimal availableAmount;
    private BigDecimal earningDaily;
}
