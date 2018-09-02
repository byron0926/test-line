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
 * @createtime： 5/22/20184:41 PM
 * @modify by： ${user}
 * @modify time： 5/22/20184:41 PM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScalperAccount implements Serializable {
    private static final long serialVersionUID = 668846580740542117L;
    private long id;
    private long partnerId;
    private String password;
    private String account;
    private long introducerId;
    private int level;
    private BigDecimal totalAmount;
    private BigDecimal freezingAmount;
    private BigDecimal availableAmount;
    private String createTime;
    private String updateTime;
    private String activeCode;
    private String lastLoginTime;
    private int isOnline;
    private int status;
    private int isAgent;
    private int agentStatus;
    private int ifCollection;
}
