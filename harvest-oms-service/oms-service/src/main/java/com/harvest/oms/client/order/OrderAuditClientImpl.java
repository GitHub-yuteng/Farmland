package com.harvest.oms.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.context.SpringHelper;
import com.harvest.core.enums.oms.OrderStatusEnum;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.request.order.audit.SubmitAuditRequest;
import com.harvest.oms.service.order.AbstractBizOrderService;
import com.harvest.oms.service.order.handler.audit.OrderAuditExecutor;
import org.apache.commons.collections4.CollectionUtils;

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
public class OrderAuditClientImpl extends AbstractBizOrderService implements OrderAuditClient {

    /**
     * 待/审核状态
     */
    private final static List<OrderStatusEnum> AUDIT_STATUS_ENUMS = Stream.of(OrderStatusEnum.APPROVE).collect(Collectors.toList());


    @Override
    public BatchExecuteResult<String> audit(Long companyId, List<Long> orderIds) {
        if (CollectionUtils.isEmpty(orderIds)) {
            return new BatchExecuteResult<>();
        }
        return this.auditWithSubmit(companyId, orderIds.stream().map(
                orderId -> {
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
                    SubmitAuditRequest submitAuditRequest = orderAuditSubmitMap.get(order.getOrderId());
                    // 传递订单信息
                    submitAuditRequest.setOrder(order);
                    // 执行审核
                    SpringHelper.getBean(OrderAuditExecutor.class).exec(companyId, submitAuditRequest);
                });
    }

}
