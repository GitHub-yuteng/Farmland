package com.harvest.oms.service.order.handler.audit;

import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.oms.client.order.OrderAuditClientImpl;
import com.harvest.oms.domain.order.OrderInfoDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 3:44 PM
 * @Description: TODO
 **/
@Component
public class OrderAuditCheckExecutor {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderAuditCheckExecutor.class);

    public final static Set<OrderStatusEnum> AUDIT_STATUS_ENUMS = OrderAuditClientImpl.AUDIT_STATUS_ENUMS;

    public void check(OrderInfoDO order) {
        if (!AUDIT_STATUS_ENUMS.contains(order.getOrderStatus())) {
            throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR,
                    "订单状态不为" + AUDIT_STATUS_ENUMS.stream().map(OrderStatusEnum::getValue).collect(Collectors.toList()) + "!"
            );
        }
    }

}
