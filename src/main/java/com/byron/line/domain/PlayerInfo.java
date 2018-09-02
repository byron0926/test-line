package com.byron.line.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: 玩家信息
 * @author： kidy
 * @createtime： 5/31/201812:30 PM
 * @modify by： ${user}
 * @modify time： 5/31/201812:30 PM
 * @desc of modify：
 * @throws:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerInfo implements Serializable {
    private static final long serialVersionUID = -1887935049356714458L;

    private long id;
    private long companyId;
    private String playerNo;
    private String playerName;
    private String nickName;
    private int channel;
    private int status;
    private String cardNo;
    private String bankAddr;
    private String notes;
    private String createTime;
    private String updateTime;
}
