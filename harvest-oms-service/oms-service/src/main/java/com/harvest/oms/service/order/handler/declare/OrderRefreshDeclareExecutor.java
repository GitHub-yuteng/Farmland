package com.harvest.oms.service.order.handler.declare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 11:54 PM
 * @Description: TODO
 **/
@Component
public class OrderRefreshDeclareExecutor {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderRefreshDeclareExecutor.class);

    public void refresh(Long companyId, Long id) {
        LOGGER.info("刷新申报信息!");
    }
}
