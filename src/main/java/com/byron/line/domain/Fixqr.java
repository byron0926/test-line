package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @desc: 固碼實體類
 * @author： kidy
 * @createtime： 5/25/20185:55 PM
 * @modify by： ${user}
 * @modify time： 5/25/20185:55 PM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fixqr implements Serializable{
    private static final long serialVersionUID = -2006928921838911203L;

    private long id;
    private long scalperInfoId;
    private long scalperId;
    private BigDecimal amount;
    private String pictureUrl;
    private int finalStatus;
    private String identifyCode;
}
