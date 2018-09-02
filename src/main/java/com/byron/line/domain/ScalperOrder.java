package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: 黄牛抢单信息
 * @author： kidy
 * @createtime： 5/31/20189:39 AM
 * @modify by： ${user}
 * @modify time： 5/31/20189:39 AM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScalperOrder implements Serializable {
    private static final long serialVersionUID = -7316546800241401346L;

    private long id;
    private long scalperId;
    private String orderNo;
    private int status;
    private String createTime;
    private String updateTime;
}
