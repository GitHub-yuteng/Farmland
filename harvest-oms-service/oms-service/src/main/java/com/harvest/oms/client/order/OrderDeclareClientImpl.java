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
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import com.harvest.oms.service.order.handler.declare.executor.OrderCancelDeclareExecutor;
import com.harvest.oms.service.order.handler.declare.executor.OrderReacquireFaceSheetExecutor;
import com.harvest.oms.service.order.handler.declare.executor.OrderRefreshDeclareExecutor;
import com.harvest.oms.service.order.handler.declare.submit.OrderSubmitDeclareExecutor;
import com.harvest.oms.vo.order.declare.OrderDeclarationVO;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 3:42 PM
 * @Description: 订单交运
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_DECLARE)
public class OrderDeclareClientImpl implements OrderDeclareClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderDeclareClientImpl.class);

    @Autowired
    private OrderDeclareRepositoryClient orderDeclareRepositoryClient;

    @Autowired
    private CacheService cacheService;

    @Override
    public Collection<OrderDeclarationVO> listDeclaration(Long companyId, List<Long> orderIds) {
        Collection<OrderDeclareSimplePO> collection = orderDeclareRepositoryClient.listDeclaration(companyId, orderIds);
        return Collections.emptyList();
    }

    @Override
    public BatchExecuteResult<String> declare(Long companyId, Collection<SubmitDeclarationRequest> submitDeclarations) {
        if (CollectionUtils.isEmpty(submitDeclarations)) {
            return new BatchExecuteResult<>();
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
                    OrderInfoDO order = SpringHelper.getBean(OrderReadClient.class).get(companyId, orderId);
                    request.setOrder(order);
                    request.setLogisticsEnum(order.getLogisticsEnum());

                    orderMap.put(orderId, order.getOrderNo());
                    // 执行申报
                    SpringHelper.getBean(OrderSubmitDeclareExecutor.class).execute(companyId, request);
                }, request -> orderMap.get(request.getId())
        );
    }

    @RepeatSubmit(seconds = 5, remind = "正在刷新, 请稍后等待结果～")
    @Override
    public void refreshDeclaration(Long companyId, List<Long> orderIds) {
        ActuatorUtils.parallelFailAllowBatchExecute(orderIds.stream().map(BatchId::build).collect(Collectors.toList()), batchId -> {
            SpringHelper.getBean(OrderRefreshDeclareExecutor.class).refresh(companyId, batchId.getId());
        });
    }

    @RepeatSubmit(seconds = 10, remind = "正在取消申报, 请耐心等待～")
    @Override
    public BatchExecuteResult<String> cancelDeclare(@RequestParam(COMPANY_ID) Long companyId, @RequestBody List<Long> orderIds) {
        Map<Long, String> orderMap = new ConcurrentHashMap<>(DEFAULT_2);
        return ActuatorUtils.parallelFailAllowBatchExecute(orderIds.stream().map(BatchId::build).collect(Collectors.toList()), batchId -> {
                    Long orderId = batchId.getId();
                    OrderInfoDO order = SpringHelper.getBean(OrderReadClient.class).get(companyId, orderId);
                    orderMap.put(orderId, order.getOrderNo());
                    SpringHelper.getBean(OrderCancelDeclareExecutor.class).cancleDeclare(companyId, order);
                }, batchId -> orderMap.get(batchId.getId())
        );
    }

    @Override
    public void saveDeclaration(Long companyId, SubmitDeclarationRequest request) {
        OrderDeclareSimplePO orderDeclareSimple = new OrderDeclareSimplePO();
        orderDeclareRepositoryClient.saveDeclaration(companyId, orderDeclareSimple);
    }

    @Override
    public BatchExecuteResult<String> reacquireDeclaration(Long companyId, List<Long> orderIds) {
        Map<Long, String> faceSheetMap = new ConcurrentHashMap<>(DEFAULT_2);
        return ActuatorUtils.parallelFailAllowBatchExecute(orderIds.stream().map(BatchId::build).collect(Collectors.toList()), batchId -> {
            String url = SpringHelper.getBean(OrderReacquireFaceSheetExecutor.class).reacquire(companyId, batchId.getId());
            faceSheetMap.put(batchId.getId(), url);
        }, batchId -> faceSheetMap.get(batchId.getId()));
    }

    @Override
    public void setLastResponse(Long companyId, Long orderId, DeclarationResponse response) {
        orderDeclareRepositoryClient.setLastResponse(companyId, orderId, response);
    }
}
