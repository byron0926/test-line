package com.byron.line.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: 訂單狀態 失效訂單狀態扭轉
 * @author： kidy
 * @createtime： 5/31/201811:59 AM
 * @modify by： ${user}
 * @modify time： 5/31/201811:59 AM
 * @desc of modify：
 * @throws:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeStatusDto implements Serializable {
    private static final long serialVersionUID = 2283050775020959190L;

    private String orderNo;
    private int status;
}
