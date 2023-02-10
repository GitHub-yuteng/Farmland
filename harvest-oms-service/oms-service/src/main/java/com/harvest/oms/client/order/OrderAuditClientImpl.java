package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.core.service.mq.ProducerMessageService;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.audit.OrderAuditTransferDTO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.request.order.audit.SubmitAuditRequest;
import com.harvest.oms.request.order.warehouse.SubmitWmsOrderMessage;
import com.harvest.oms.service.order.AbstractBizOrderService;
import com.harvest.oms.service.order.business.OrderAuditProcessor;
import com.harvest.oms.service.order.event.OrderEventPublisher;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 3:42 PM
 * @Description: 订单审核
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_AUDIT)
public class OrderAuditClientImpl extends AbstractBizOrderService implements OrderAuditClient, OrderAuditProcessor {

    /**
     * 待/审核状态
     */
    private final static List<OrderStatusEnum> AUDIT_STATUS_ENUMS = Stream.of(OrderStatusEnum.APPROVE).collect(Collectors.toList());

    @Autowired
    private ProducerMessageService producerMessageService;

    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Override
    public BatchExecuteResult<String> audit(Long companyId, List<Long> orderIds) {
        if (CollectionUtils.isEmpty(orderIds)) {
            return new BatchExecuteResult<>();
        }
        return this.auditWithSubmit(companyId, orderIds.stream().map(orderId -> {
            SubmitAuditRequest submitAuditRequest = new SubmitAuditRequest();
            submitAuditRequest.setId(orderId);
            return submitAuditRequest;
        }).collect(Collectors.toList()));
    }

    @Override
    public BatchExecuteResult<String> auditWithSubmit(Long companyId, Collection<SubmitAuditRequest> requests) {
        if (CollectionUtils.isEmpty(requests) || requests.parallelStream().allMatch(request -> Objects.isNull(request.getId()))) {
            return new BatchExecuteResult<>();
        }
        Map<Long, SubmitAuditRequest> orderAuditSubmitMap = requests.stream().collect(Collectors.toMap(SubmitAuditRequest::getId, Function.identity(), (k1, k2) -> k1));
        // 记录键值 id-key
        Map<Long, String> orderMap = new HashMap<>(DEFAULT_2);
        return super.SyncOrderParallelFailAllowBatchExecute(companyId, orderAuditSubmitMap.keySet(),
                order -> {
                    // 传递订单信息
                    SubmitAuditRequest submitAuditRequest = orderAuditSubmitMap.get(order.getOrderId());
                    submitAuditRequest.setOrder(order);
                    // 执行审核
                    this.executeAudit(companyId, submitAuditRequest);
                });
    }

    @Override
    public void check(Long companyId, SubmitAuditRequest request) {

    }

    @Override
    public OrderAuditTransferDTO beforeAudit(Long companyId, SubmitAuditRequest request) {
        return null;
    }

    @Override
    public void processAudit(Long companyId, SubmitAuditRequest request, OrderAuditTransferDTO transfer) {


    }

    @Override
    public void afterAudit(Long companyId, SubmitAuditRequest request) {
        // 推送wms
        SubmitWmsOrderMessage submitWmsOrderMessage = new SubmitWmsOrderMessage();
        submitWmsOrderMessage.setCompanyId(companyId);
        submitWmsOrderMessage.setOrder(request.getOrder());
        SendResult sendResult = producerMessageService.syncSend("my-topic", submitWmsOrderMessage);
        System.out.println(JsonUtils.object2Json(sendResult));
        // 发布订单审核事件
        orderEventPublisher.publishAsync(companyId, request.getOrder().getOrderId(), OrderEventEnum.AUDIT);
    }

}
