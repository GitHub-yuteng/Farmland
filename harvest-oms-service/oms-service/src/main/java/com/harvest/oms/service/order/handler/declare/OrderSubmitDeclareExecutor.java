package com.harvest.oms.service.order.handler.declare;

import com.harvest.basic.client.logistics.BasicLogisticsClient;
import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.client.logistics.LogisticsReadClient;
import com.harvest.oms.domain.logistics.LogisticsChannelAddressDO;
import com.harvest.oms.domain.logistics.LogisticsChannelDO;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.declare.OrderDeclarationDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import com.harvest.oms.service.order.event.OrderEventPublisher;
import com.harvest.oms.service.order.handler.OrderDeclareHandler;
import com.harvest.oms.service.order.processor.OrderDeclareProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 3:44 PM
 * @Description: TODO
 **/
@Component
public class OrderSubmitDeclareExecutor implements OrderDeclareProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderSubmitDeclareExecutor.class);

    @Autowired
    private BasicLogisticsClient basicLogisticsClient;

    @Autowired
    private LogisticsReadClient logisticsReadClient;

    @Autowired(required = false)
    private List<OrderDeclareHandler> orderDeclareHandlers;

    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Override
    public void check(Long companyId, SubmitDeclarationRequest request) {
        LogisticsChannelDO logisticsChannel = request.getOrder().getLogisticsChannel();
        if (Objects.isNull(logisticsChannel)) {
            throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, "渠道为空, 请选择承运商渠道后, 进行交运申报!");
        }
        // 是否已经申报
        if (this.isDeclared(request.getOrder())) {
            throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, "[warn]当前订单已申请交运，如需重新交运请先取消或者点击【刷新】查看交运结果!");
        }
    }

    private boolean isDeclared(OrderInfoDO order) {
        OrderDeclarationDO declaration = order.getDeclaration();
        return Objects.nonNull(declaration) && order.getWaitDeclare();
    }

    @Override
    public boolean beforeDeclare(Long companyId, SubmitDeclarationRequest request) {

        // 渠道类型
        LogisticsChannelDO logisticsChannel = request.getOrder().getLogisticsChannel();
        request.setLogisticsType(LogisticsEnum.getEnumByCode(logisticsChannel.getLogisticsCode()));
        // 渠道地址信息
        List<LogisticsChannelAddressDO> channelAddressList = logisticsReadClient.getChannelAddress(companyId, logisticsChannel.getChannelId());
        request.setChannelAddressList(channelAddressList);

        return true;
    }

    @Override
    public DeclarationResponse processDeclare(Long companyId, SubmitDeclarationRequest request) {
        LOGGER.info("OrderDeliveryClientImpl#processDeclare#申报请求: " + JsonUtils.object2Json(request));
        DeclarationResponse response = basicLogisticsClient.submitDeclaration(companyId, request);
        LOGGER.info("OrderDeliveryClientImpl#processDeclare#申报结果: " + JsonUtils.object2Json(response));
        orderDeclareHandlers.forEach(handler -> handler.execute(companyId, request, response));
        return response;
    }

    @Override
    public void afterDeclare(Long companyId, SubmitDeclarationRequest request, DeclarationResponse response) {
        // 发布订单审核事件
        orderEventPublisher.publishAsync(companyId, request.getOrder().getOrderId(), OrderEventEnum.DECLARE);
        // 上传文件

    }
}
