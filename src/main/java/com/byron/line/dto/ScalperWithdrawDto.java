package com.byron.line.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @desc: 黃牛提現消息傳輸實體類
 * @author： kidy
 * @createtime： 5/26/20183:45 PM
 * @modify by： ${user}
 * @modify time： 5/26/20183:45 PM
 * @desc of modify：
 * @throws:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScalperWithdrawDto implements Serializable {
    private static final long serialVersionUID = 5734755739045007732L;
    private long scalperId;
    private BigDecimal amount;
    private long tradeId;
}
