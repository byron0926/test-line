package com.byron.line.dto;

import com.byron.line.domain.PlayerInfo;
import com.byron.line.domain.Trade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: 交易訂單和玩家信息
 * @author： kidy
 * @createtime： 5/31/201812:28 PM
 * @modify by： ${user}
 * @modify time： 5/31/201812:28 PM
 * @desc of modify：
 * @throws:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradePlayerDto implements Serializable {
    private static final long serialVersionUID = 7487894545559192538L;

    private Trade trade;
    private PlayerInfo playerInfo;
}
