package com.byron.line.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @desc: OCR解析返回数据
 * @author： kidy
 * @createtime： 5/29/20186:17 PM
 * @modify by： ${user}
 * @modify time： 5/29/20186:17 PM
 * @desc of modify：
 * @throws:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OCRInfoDto implements Serializable {
    private static final long serialVersionUID = -1817474496694897784L;
    /*渠道类型 3是支付宝 4是银行卡 */
    private int type;
    /*交易类型 收款或付款 receive or pay*/
    private String txType;
    /*订单号*/
    private String orderNum;
    /*金额*/
    private BigDecimal amount;
    /*交易日期*/
    private String orderDate;
    /*付款 接收者 玩家名称*/
    private String receiver;
    /*付款 接收者账号 支付宝账号/银行卡号*/
    private String receiveAccount;
    /*交易状态 1 处理中 2 成功 3 失败*/
    private int status;
}
