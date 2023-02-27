package com.harvest.oms.service.order.handler.declare.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 11:54 PM
 * @Description: TODO
 **/
@Component
public class OrderReacquireFaceSheetExecutor {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderReacquireFaceSheetExecutor.class);

    public String reacquire(Long companyId, Long orderId) {
        return "url";
    }
}
