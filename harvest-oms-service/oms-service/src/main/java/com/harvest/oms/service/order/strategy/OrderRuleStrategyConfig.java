package com.harvest.oms.service.order.strategy;

import com.harvest.oms.service.order.event.order.OrderLogisticsRuleMatchEvent;
import com.harvest.oms.service.order.event.order.OrderWarehouseRuleMatchEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 6:14 PM
 * @Description: TODO
 **/
@Configuration
public class OrderRuleStrategyConfig {

    /**
     * 匹配仓库
     *
     * @param event
     * @return
     */
    @Bean
    @Order(10)
    public OrderRuleStrategy matchWarehouse(OrderWarehouseRuleMatchEvent event) {
        return event::match;
    }


    /**
     * 匹配物流
     *
     * @param event
     * @return
     */
    @Bean
    @Order(20)
    public OrderRuleStrategy matchLogistics(OrderLogisticsRuleMatchEvent event) {
        return event::match;
    }

}
