package com.byron.line.client;

import com.alibaba.fastjson.JSONObject;
import com.byron.line.common.domain.ResponseResult;
import com.byron.line.common.enums.SystemCodeEnum;
import com.byron.line.common.exception.IllegalOptaionException;
import com.byron.line.common.util.DateUtils;
import com.byron.line.common.util.HttpClient;
import com.byron.line.common.util.StringUtils;
import com.byron.line.domain.PlayerInfo;
import com.byron.line.domain.Trade;
import com.byron.line.dto.TradePlayerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @desc: 对接订单服务
 * @author： kidy
 * @createtime： 6/11/20188:23 PM
 * @modify by： ${user}
 * @modify time： 6/11/20188:23 PM
 * @desc of modify：
 * @throws:
 */
public class LexusClient {
    private static final Logger log = LoggerFactory.getLogger(LexusClient.class);

    private static final String HTTP_DOMAIN = "http://47.104.235.121:8092";
    /*抢单接口*/
    private static final String SEARCH_ORDER_HTTP_URL = HTTP_DOMAIN + "/dora/player/matchOrder";
    /*取消订单接口*/
    private static final String CANCEL_ORDER_HTTP_URL = HTTP_DOMAIN + "/dora/player/cancelOrder";

    /**
     * 抢单
     * @param scalperId 黄牛ID
     * @return
     */
    public static ResponseResult matchOrder(long scalperId){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("scalperId",String.valueOf(scalperId));
            HttpClient.ResponseResult re = HttpClient.newInstance().sendHttpPost(SEARCH_ORDER_HTTP_URL,jsonObject.toJSONString(),null,null);
            if (StringUtils.isEmpty(re)){
                throw new IllegalOptaionException(SystemCodeEnum.HTTP_NO_RESPONSE);
            }
            if (re.getHttpCode()==200){
                JSONObject obj = JSONObject.parseObject(re.getContent());
                JSONObject data = obj.getJSONObject("data");
                TradePlayerDto tradePlayerDto = null;
                if (StringUtils.isNotEmpty(data)){
                    tradePlayerDto = TradePlayerDto.builder()
                            .trade(Trade.builder()
                                    .balane(data.getBigDecimal("balance"))
                                    .earning(data.getBigDecimal("earning"))
                                    .tradeNo(data.getString("orderNo"))
                                    .disableTime(data.getString("invalidDate"))
                                    .status(data.getInteger("status"))
                                    .now(DateUtils.dateToString(new Date(),DateUtils.YYYY_MM_DD_HH_MM_SS))
                                    .build())
                            .playerInfo(PlayerInfo.builder()
                                    .id(data.getLong("playerId"))
                                    .playerName(data.getString("playerName"))
                                    .channel(data.getInteger("channelCode"))
                                    .cardNo(data.getString("cardNo"))
                                    .build())
                            .build();
                } else {
                    log.info("抢单查询data为空:{}",obj);
                }
                return ResponseResult.builder()
                        .code(obj.getInteger("code"))
                        .msg(obj.getString("msg"))
                        .data(tradePlayerDto)
                        .build();
            }
        } catch (Exception e) {
            throw new IllegalOptaionException(SystemCodeEnum.SYSTEM_ERROR);
        }
        throw new IllegalOptaionException(SystemCodeEnum.HTTP_NO_RESPONSE);
    }

    /**
     * 取消订单
     * @param orderNo 订单编号
     * @return
     */
    public static ResponseResult cancleOrder(String orderNo){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderNo",orderNo);
            HttpClient.ResponseResult re = HttpClient.newInstance().sendHttpPost(CANCEL_ORDER_HTTP_URL,jsonObject.toJSONString(),null,null);
            if (StringUtils.isEmpty(re)){
                throw new IllegalOptaionException(SystemCodeEnum.HTTP_NO_RESPONSE);
            }
            if (re.getHttpCode()==200){
                JSONObject obj = JSONObject.parseObject(re.getContent());
                return ResponseResult.builder()
                        .code(obj.getInteger("code"))
                        .msg(obj.getString("msg"))
                        .data(obj.get("data"))
                        .build();
            }
        }  catch (Exception e) {
            throw new IllegalOptaionException(SystemCodeEnum.SYSTEM_ERROR);
        }
        throw new IllegalOptaionException(SystemCodeEnum.HTTP_NO_RESPONSE);
    }

    public static void main(String[] args) {
        System.out.println(matchOrder(34));
//        System.out.println(cancleOrder("123"));
    }
}
