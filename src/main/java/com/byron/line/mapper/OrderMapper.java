package com.byron.line.mapper;

import com.byron.line.domain.OrderDto;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    Long insertOrder(OrderDto orderDto);
    int updateOrder(OrderDto orderDto);
    int queryOrderByRandomCode(String randomCode);
    OrderDto queryOrderById(Long id);
}
