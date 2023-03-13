package com.harvest.oms.client.order;

import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.core.annotation.RepeatSubmit;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.batch.BatchId;
import com.harvest.core.context.SpringHelper;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.service.redis.CacheService;
import com.harvest.core.utils.ActuatorUtils;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.client.order.OrderDeclareRepositoryClient;
import com.harvest.oms.repository.domain.declare.OrderDeclareSimplePO;
import com.harvest.oms.repository.domain.declare.OrderItemDeclareSimplePO;
import com.harvest.oms.repository.enums.declare.DeclareStatusEnum;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import com.harvest.oms.service.order.AbstractBizOrderService;
import com.harvest.oms.service.order.handler.declare.executor.OrderCancelDeclareExecutor;
import com.harvest.oms.service.order.handler.declare.executor.OrderReacquireFaceSheetExecutor;
import com.harvest.oms.service.order.handler.declare.executor.OrderRefreshDeclareExecutor;
import com.harvest.oms.service.order.handler.declare.submit.OrderSubmitDeclareExecutor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 3:42 PM
 * @Description: 订单交运
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_DECLARE)
public class OrderDeclareClientImpl extends AbstractBizOrderService implements OrderDeclareClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDeclareClientImpl.class);

    @Autowired
    private OrderDeclareRepositoryClient orderDeclareRepositoryClient;

    @Autowired
    private CacheService cacheService;

    @Override
    public Collection<OrderDeclareSimplePO> listDeclaration(Long companyId, List<Long> orderIds) {
        return orderDeclareRepositoryClient.listDeclaration(companyId, orderIds);
    }

    @Override
    public BatchExecuteResult<String> declare(Long companyId, Collection<SubmitDeclarationRequest> submitDeclarations) {
        if (CollectionUtils.isEmpty(submitDeclarations)) {
            return BatchExecuteResult.empty();
        }
        submitDeclarations = submitDeclarations.stream().filter(request -> Objects.nonNull(request.getId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(submitDeclarations)) {
            throw new StandardRuntimeException("请检查订单交运申报参数! By: orderId is null. ");
        }
        // 记录键值 id-key
        Map<Long, String> orderMap = new ConcurrentHashMap<>(DEFAULT_2);
        // TODO OMS Concurrent processing of each order delivery declaration.
        return ActuatorUtils.parallelFailAllowBatchExecute(submitDeclarations, (request) -> {
                    Long orderId = request.getId();
                    OrderInfoDO order = SpringHelper.getBean(OrderReadClient.class).getOrderRich(companyId, orderId);
                    request.setOrder(order);
                    request.setLogisticsEnum(order.getLogisticsEnum());

                    orderMap.put(orderId, order.getOrderNo());
                    // 执行申报
                    SpringHelper.getBean(OrderSubmitDeclareExecutor.class).execute(companyId, request);
                }, request -> orderMap.get(request.getId())
        );
    }

    @RepeatSubmit(seconds = 5, remind = "正在刷新, 请稍后～")
    @Override
    public void refreshDeclaration(Long companyId, List<Long> orderIds) {
        ActuatorUtils.parallelFailAllowBatchExecute(orderIds.stream().map(BatchId::build).collect(Collectors.toList()), batchId -> {
            SpringHelper.getBean(OrderRefreshDeclareExecutor.class).refresh(companyId, batchId.getId());
        });
    }

    @RepeatSubmit(seconds = 10, remind = "正在取消申报, 请耐心等待～")
    @Override
    public BatchExecuteResult<String> cancelDeclare(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds) {
        return super.SyncUniqueOrderParallelFailAllowBatchExecute(companyId, orderIds, order -> {
            SpringHelper.getBean(OrderCancelDeclareExecutor.class).cancleDeclare(companyId, order);
        });
    }

    @Override
    public void draftDeclaration(Long companyId, SubmitDeclarationRequest request) {
        OrderInfoDO order = request.getOrder();
        OrderDeclareSimplePO orderDeclareSimple = new OrderDeclareSimplePO();
        orderDeclareSimple.setOrderId(order.getOrderId());
        orderDeclareSimple.setCompanyId(companyId);
        orderDeclareSimple.setStatus(DeclareStatusEnum.INIT.getKey());
        orderDeclareSimple.setItems(order.getOrderItems().stream().map(orderItemDO -> {
            OrderItemDeclareSimplePO orderItemDeclareSimplePO = new OrderItemDeclareSimplePO();
            orderItemDeclareSimplePO.setOrderItemId(orderItemDO.getOrderItemId());
            orderItemDeclareSimplePO.setCompanyId(orderItemDO.companyId);
            orderItemDeclareSimplePO.setOrderId(orderItemDO.getOrderId());
            orderItemDeclareSimplePO.setSpuId(orderItemDO.getSpuId());
            orderItemDeclareSimplePO.setSkuId(orderItemDO.getSkuId());
            orderItemDeclareSimplePO.setQuantity(orderItemDO.getQuantity());
            return orderItemDeclareSimplePO;
        }).collect(Collectors.toList()));
        orderDeclareRepositoryClient.draftDeclaration(companyId, orderDeclareSimple);
    }

    @RepeatSubmit(seconds = 10, remind = "正在重新获取面单数据, 请耐心等待～")
    @Override
    public void reacquireDeclaration(Long companyId, List<Long> orderIds) {
        // 串行可失败处理器
        ActuatorUtils.failAllowBatchExecute(orderIds.stream().map(BatchId::build).collect(Collectors.toList()), batchId -> {
            SpringHelper.getBean(OrderReacquireFaceSheetExecutor.class).reacquire(companyId, batchId.getId());
        }, Function.identity());
    }

    @Override
    public void setLastResponse(Long companyId, Long orderId, DeclarationResponse response) {
        orderDeclareRepositoryClient.setLastResponse(companyId, orderId, response);
    }
}
