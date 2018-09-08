package com.byron.line.mapper;

import com.byron.line.domain.OrderDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {


//    @Insert(" insert into t_line_order (player_name,company_id,company_Order_no,amount," +
//            "type,note,player_account, random_code, order_crt_time,order_no,status,channel_code," +
//            "terminal,player_id, qrcode_url) values(#{playerName},#{companyId},#{companyOrderNo}, #{amount}," +
//            " #{type}, #{note}, #{playerAccount},#{randomCode},#{orderCrtTime},#{orderNo},#{status}," +
//            "#{channelCode}, #{terminal}, #{playerId}, #{qrcodeUrl})")
//    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertOrder(OrderDto orderDto);
    int updateOrder(OrderDto orderDto);
    int queryOrderByRandomCode(String randomCode);
    OrderDto queryOrderById(Long id);
}
