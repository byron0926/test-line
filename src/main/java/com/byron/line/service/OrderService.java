package com.byron.line.service;

import com.byron.line.common.util.ResponseResult;
import com.byron.line.domain.OrderDto;

public interface OrderService {



    ResponseResult insertOrderAndReturnEntity(OrderDto orderDto);
    ResponseResult updateOrder(OrderDto orderDto);
    /*定时器轮询20分钟内的订单，超时后置为over time invalid*/
    ResponseResult setOverTimeStatus(Long id);

}
