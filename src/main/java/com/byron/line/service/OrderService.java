package com.byron.line.service;

import com.byron.line.common.util.ResponseResult;
import com.byron.line.domain.OrderDto;

public interface OrderService {



    ResponseResult insertOrderAndReturnEntity(OrderDto orderDto);
    ResponseResult updateOrder(OrderDto orderDto);

}
