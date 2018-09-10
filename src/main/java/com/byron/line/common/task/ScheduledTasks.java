package com.byron.line.common.task;

import com.byron.line.domain.OrderDto;
import com.byron.line.mapper.OrderMapper;
import com.byron.line.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void timer() {
        List<OrderDto> list = orderMapper.timerQuery();
        for(OrderDto orderDto:list){
            logger.info("超时订单信息={}",orderDto);
            orderService.setOverTimeStatus(orderDto.getId());
        }
    }
}

