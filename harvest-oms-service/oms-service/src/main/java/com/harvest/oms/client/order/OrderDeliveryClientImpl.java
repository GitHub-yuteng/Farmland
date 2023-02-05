package com.harvest.oms.client.order;

import com.harvest.basic.client.logistics.BasicLogisticsClient;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.context.SpringHelper;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.utils.ActuatorUtils;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.declare.OrderDeclarationDO;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 3:42 PM
 * @Description: 订单交运
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_DELIVERY)
public class OrderDeliveryClientImpl implements OrderDeliveryClient {

    @Autowired
    private BasicLogisticsClient basicLogisticsClient;

    @Override
    public void listDeclaration(Long companyId, List<Long> orderIds) {

    }

    @Override
    public BatchExecuteResult<String> declare(Long companyId, Collection<SubmitDeclarationRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) {
            return new BatchExecuteResult<>();
        }
        requests = requests.stream().filter(request -> Objects.nonNull(request.getOrderId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(requests)) {
            throw new StandardRuntimeException("请检查订单交运申报参数! By: orderId is null. ");
        }
        Map<Long, String> orderMap = new HashMap<>(2);
        // TODO OMS Concurrent processing of each order delivery declaration.
        return ActuatorUtils.parallelFailAllowBatchExecute(requests, (request) -> {
                    Long orderId = request.getOrderId();
                    OrderInfoDO order = SpringHelper.getBean(OrderReadClient.class).get(companyId, orderId);
                    // 是否已经申报
                    if (this.existDeclaration(order)) {
                        throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, "[warn]当前订单已申请交运，如需重新交运请先取消或者点击【刷新】查看交运结果!");
                    }
                    // 订单信息
                    request.setOrder(order);
                    // 渠道地址信息

                    orderMap.put(orderId, order.getOrderNo());
                    // 提交报关
                    basicLogisticsClient.submitDeclaration(companyId, request);

                }, request -> orderMap.get(request.getOrderId())
        );
    }

    private boolean existDeclaration(OrderInfoDO order) {
        OrderDeclarationDO declaration = order.getDeclaration();
        return Objects.nonNull(declaration);
    }

}
