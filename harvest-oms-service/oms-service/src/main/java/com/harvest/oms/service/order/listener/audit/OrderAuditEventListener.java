package com.harvest.oms.service.order.listener.audit;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.service.order.listener.OrderEventListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/9 4:31 PM
 * @Description: TODO
 **/
@Component
public class OrderAuditEventListener implements OrderEventListener {

    @Override
    public OrderEventEnum type() {
        return OrderEventEnum.AUDIT;
    }

    @Override
    public void audit(Long companyId, OrderInfoDO order) {
        System.out.println("audit:" + order.getOrderId());
    }
}
