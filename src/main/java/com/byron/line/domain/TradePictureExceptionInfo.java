package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @desc: 截图信息
 * @author： kidy
 * @createtime： 5/30/201812:24 PM
 * @modify by： ${user}
 * @modify time： 5/30/201812:24 PM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradePictureExceptionInfo implements Serializable {
    private static final long serialVersionUID = -8494803969587190644L;

    private long id;
    private long scalperId;
    private String pictureUrl;
    private String uploadTime;
    private String trueTime;
    private BigDecimal trueBalance;
    private int status;
    private String tradeSeriesNo;
    private long partnerId;
    private int ocrType;
    private int tradeType;
    private String reason;
    private int isFirst;
    private String orderNo;
}
