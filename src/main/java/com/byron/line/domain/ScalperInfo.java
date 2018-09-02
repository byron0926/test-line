package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: 黃牛支付寶信息
 * @author： kidy
 * @createtime： 5/25/20184:15 PM
 * @modify by： ${user}
 * @modify time： 5/25/20184:15 PM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScalperInfo implements Serializable {
    private static final long serialVersionUID = -6734141838920275006L;
    private long id;
    private long scalperId;
    private String alipayName;
    private String alipayAccount;
    private int channel;
    private int status;
    private String nickName;
    private String zsign;
    private String remark;
    private String createTime;
    private String updateTIme;
}
