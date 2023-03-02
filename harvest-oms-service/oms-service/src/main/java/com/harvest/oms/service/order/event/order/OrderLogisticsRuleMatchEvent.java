package com.harvest.oms.service.order.event.order;

import com.harvest.core.context.SpringHelper;
import com.harvest.oms.client.order.OrderMatchClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.service.order.processor.OrderLogisticsMatchProcessor;
import com.harvest.rule.domain.logistics.LogisticsRuleMatch;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRuleCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 6:24 PM
 * @Description: 物流匹配
 **/
@Component
public class OrderLogisticsRuleMatchEvent extends AbstractMatchEvent implements OrderLogisticsMatchProcessor {

    protected static final Logger LOGGER = LoggerFactory.getLogger(OrderLogisticsRuleMatchEvent.class);

    /**
     * 匹配物流
     *
     * @param companyId
     * @param orderId
     */
    public void match(Long companyId, Long orderId) {
        OrderInfoDO order = super.orderReadClient.getOrderRich(companyId, orderId);
        this.match(companyId, order);
    }

    public void match(Long companyId, OrderInfoDO order) {
        LogisticsRuleCondition condition = new LogisticsRuleCondition();
        LogisticsRuleMatch match = SpringHelper.getBean(OrderMatchClient.class).matchLogistics(companyId, condition);
        if(Objects.isNull(match)){
            LOGGER.error("");
        }
    }
}
