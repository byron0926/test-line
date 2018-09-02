package com.byron.line.dto;

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
public class FixqrDto implements Serializable{
    private static final long serialVersionUID = 9141287268771646113L;
    private BigDecimal amount;
    private String pictureUrl;
}
