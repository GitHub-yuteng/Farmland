package com.harvest.oms.client.order;

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
import com.harvest.oms.service.order.handler.declare.OrderCancelDeclareExecutor;
import com.harvest.oms.service.order.handler.declare.OrderDeclareExecutor;
import com.harvest.oms.service.order.handler.declare.OrderRefreshDeclareExecutor;
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
                    orderMap.put(orderId, order.getOrderNo());
                    // 执行申报
                    SpringHelper.getBean(OrderDeclareExecutor.class).execute(companyId, request);
                }, request -> orderMap.get(request.getId())
        );
    }

    @RepeatSubmit(seconds = 5)
    @Override
    public Collection<OrderDeclarationVO> refreshDeclaration(Long companyId, List<Long> orderIds) {
        ActuatorUtils.parallelFailAllowBatchExecute(orderIds.stream().map(BatchId::build).collect(Collectors.toList()), batchId -> {
            SpringHelper.getBean(OrderRefreshDeclareExecutor.class).refresh(companyId, batchId.getId());
        });
        // result 结果处理 重新查询 申报结果返回
        return this.listDeclaration(companyId, orderIds);
    }

    @RepeatSubmit(seconds = 10)
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
}
