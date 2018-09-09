package com.byron.line.mapper;

import com.byron.line.domain.OrderDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    int insertOrder(OrderDto orderDto);
    int updateOrder(OrderDto orderDto);
    int notify(String orderNo);

    int queryOrderByRandomCode(String randomCode);
    OrderDto queryOrderById(Long id);

    void setOverTimeStatus(Long id);

    List<OrderDto> timerQuery();
}
