package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.context.SpringHelper;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.service.order.AbstractBizOrderService;
import com.harvest.rule.client.logistics.LogisticsRuleClient;
import com.harvest.rule.client.warehouse.WarehouseRuleClient;
import com.harvest.rule.domain.logistics.LogisticsRuleMatch;
import com.harvest.rule.domain.warehouse.WarehouseRuleMatch;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRuleCondition;
import com.harvest.rule.repository.domain.match.warehouse.WarehouseRuleCondition;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 3:42 PM
 * @Description: 订单审核
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_MATCH)
public class OrderMatchClientImpl extends AbstractBizOrderService implements OrderMatchClient {

    @Override
    public WarehouseRuleMatch matchWarehouse(Long companyId, WarehouseRuleCondition condition) {
        return SpringHelper.getBean(WarehouseRuleClient.class).matchWarehouse(companyId, condition);
    }

    @Override
    public LogisticsRuleMatch matchLogistics(Long companyId, LogisticsRuleCondition condition) {
        return SpringHelper.getBean(LogisticsRuleClient.class).matchLogistics(companyId, condition);
    }

}
