package com.harvest.oms.service.order.event.order;

import com.harvest.oms.domain.order.OrderInfoDO;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 6:24 PM
 * @Description: 赠品匹配
 **/
@Component
public class OrderGiftGoodsRuleMatchEvent extends AbstractMatchEvent {

    /**
     * 赠品匹配
     *
     * @param companyId
     * @param orderId
     */
    public void match(Long companyId, Long orderId) {
        OrderInfoDO order = super.orderReadClient.get(companyId, orderId);
        this.match(companyId, order);
    }

    public void match(Long companyId, OrderInfoDO order) {

    }
}
