package com.byron.line.mapper;

import com.byron.line.domain.OrderDto;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskFlowMapper {

    int insertTaskFlow(OrderDto orderDto);
    int queryByOrderNo(String orderNo);

}
