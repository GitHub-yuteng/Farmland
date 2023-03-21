package com.harvest.oms.service.order.handler.update;

import com.harvest.core.service.annotation.BizLog;
import com.harvest.oms.client.order.OrderMatchClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import com.harvest.rule.domain.logistics.LogisticsRuleMatch;
import com.harvest.rule.repository.domain.match.logistics.LogisticsRuleCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 10:54 AM
 * @Description: TODO
 **/
@Component
public class OrderUpdateLogisticsMatchHandler extends AbstractBizOrderHandler implements OrderUpdateHandler {

    @Autowired
    private OrderMatchClient orderMatchClient;

    @Override
    protected String update() {
        return "更新订单物流[匹配]";
    }

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.MATCH_LOGISTICS.equals(updateEnum);
    }


    @BizLog
    @Override
    public void handle(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        LogisticsRuleCondition condition = new LogisticsRuleCondition();
        LogisticsRuleMatch logisticsRuleMatch = orderMatchClient.matchLogistics(companyId, condition);
        this.log(companyId, field, order);
    }

    /**
     * 记录处理日志
     *
     * @param companyId
     * @param field
     * @param order
     */
    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {

    }

}
