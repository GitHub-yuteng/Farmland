package com.harvest.oms.service.order.handler.update;

import com.harvest.core.annotation.BizLog;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.client.order.OrderWriteRepositoryClient;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 10:54 AM
 * @Description: TODO
 **/
@Component
public class OrderUpdateLogisticsHandler extends AbstractBizOrderHandler implements OrderUpdateHandler {

    @Autowired
    private OrderWriteRepositoryClient orderWriteRepositoryClient;

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.LOGISTICS.equals(updateEnum);
    }

    @Override
    public void check(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        OrderUpdateHandler.super.check(companyId, field, order);
    }

    @BizLog
    @Override
    public void handle(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {

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

    @Override
    protected String update() {
        return "更新订单物流";
    }
}
