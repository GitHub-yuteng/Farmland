package com.harvest.oms.service.order.handler.audit;

import com.harvest.core.annotation.BizLog;
import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.core.log.AbstractOperationLog;
import com.harvest.core.service.mq.ProducerMessageService;
import com.harvest.core.service.utils.BizLogUtils;
import com.harvest.oms.client.order.OrderWriteClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.request.order.audit.SubmitAuditReturnRequest;
import com.harvest.oms.service.order.event.OrderEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 3:44 PM
 * @Description: 订单打回审核执行器
 **/
@Component
public class OrderAuditReturnExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderAuditReturnExecutor.class);

    @Autowired
    private OrderWriteClient orderWriteClient;

    @Autowired
    private ProducerMessageService producerMessageService;

    @Autowired
    private OrderEventPublisher orderEventPublisher;

    /**
     * 执行处理
     *
     * @param companyId
     * @param request
     */
    @BizLog
    public void exec(Long companyId, SubmitAuditReturnRequest request) {
        OrderInfoDO order = request.getOrder();
        order.setOrderStatus(OrderStatusEnum.APPROVE);
        orderWriteClient.updateOrderStatus(companyId, order);
        this.log(companyId, request);
    }

    public void log(Long companyId, SubmitAuditReturnRequest request) {
        OrderOperationLog log = OrderOperationLog.init();
        log.setCompanyId(companyId);
        log.setBusinessId(request.getOrder().getOrderId());
        log.setOrderNo(request.getOrder().getOrderNo());
        log.setOperationType(AbstractOperationLog.OperationType.MODIFY);
        log.setContent("订单打回审核");
        BizLogUtils.log(log);
    }

}
