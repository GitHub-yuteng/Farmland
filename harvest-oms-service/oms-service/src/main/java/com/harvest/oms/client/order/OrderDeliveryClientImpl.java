package com.harvest.oms.client.order;

import com.harvest.basic.client.logistics.BasicLogisticsClient;
import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.context.SpringHelper;
import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.utils.ActuatorUtils;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.client.logistics.LogisticsReadClient;
import com.harvest.oms.domain.logistics.LogisticsChannelAddressDO;
import com.harvest.oms.domain.logistics.LogisticsChannelDO;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.declare.OrderDeclarationDO;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import com.harvest.oms.service.order.business.OrderDeclareProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 3:42 PM
 * @Description: 订单交运
 **/
@HarvestService(path = HarvestOmsApplications.Path.ORDER_DELIVERY)
public class OrderDeliveryClientImpl implements OrderDeliveryClient, OrderDeclareProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderDeliveryClientImpl.class);

    @Autowired
    private BasicLogisticsClient basicLogisticsClient;

    @Autowired
    private LogisticsReadClient logisticsReadClient;

    @Override
    public void listDeclaration(Long companyId, List<Long> orderIds) {

    }

    @Override
    public BatchExecuteResult<String> declare(Long companyId, Collection<SubmitDeclarationRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) {
            return new BatchExecuteResult<>();
        }
        requests = requests.stream().filter(request -> Objects.nonNull(request.getId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(requests)) {
            throw new StandardRuntimeException("请检查订单交运申报参数! By: orderId is null. ");
        }

        // 记录键值 id-key
        Map<Long, String> orderMap = new HashMap<>(2);
        // TODO OMS Concurrent processing of each order delivery declaration.
        return ActuatorUtils.parallelFailAllowBatchExecute(requests, (request) -> {
                    Long orderId = request.getId();
                    OrderInfoDO order = SpringHelper.getBean(OrderReadClient.class).get(companyId, orderId);
                    request.setOrder(order);
                    orderMap.put(orderId, order.getOrderNo());
                    // 执行申报
                    this.executeDeclare(companyId, request);
                }, request -> orderMap.get(request.getId())
        );
    }

    @Override
    public void check(Long companyId, SubmitDeclarationRequest request) {
        LogisticsChannelDO logisticsChannel = request.getOrder().getLogisticsChannel();
        if (Objects.isNull(logisticsChannel)) {
            throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, "渠道为空, 请选择承运商渠道后, 进行交运申报!");
        }
        // 是否已经申报
        if (this.existDeclaration(request.getOrder())) {
            throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, "[warn]当前订单已申请交运，如需重新交运请先取消或者点击【刷新】查看交运结果!");
        }
    }

    private boolean existDeclaration(OrderInfoDO order) {
        OrderDeclarationDO declaration = order.getDeclaration();
        return Objects.nonNull(declaration);
    }

    @Override
    public void beforeDeclare(Long companyId, SubmitDeclarationRequest request) {

    }

    @Override
    public DeclarationResponse processDeclare(Long companyId, SubmitDeclarationRequest request) {

        LogisticsChannelDO logisticsChannel = request.getOrder().getLogisticsChannel();
        request.setLogisticsType(LogisticsEnum.getEnumByCode(logisticsChannel.getLogisticsCode()));
        // 渠道地址信息
        List<LogisticsChannelAddressDO> channelAddressList = logisticsReadClient.getChannelAddress(companyId, logisticsChannel.getChannelId());
        request.setChannelAddressList(channelAddressList);
        // 提交报关
        LOGGER.info("申报请求: " + JsonUtils.object2Json(request));
        DeclarationResponse response = basicLogisticsClient.submitDeclaration(companyId, request);

        Boolean success = response.getSuccess();
        if (success) {

        }


        return response;
    }

    @Override
    public void afterDeclare(Long companyId, SubmitDeclarationRequest request, DeclarationResponse response) {

        // 上传文件


    }

}
