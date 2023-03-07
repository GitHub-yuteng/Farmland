package com.harvest.oms.service.order.handler.declare.submit;

import com.harvest.basic.client.logistics.BasicLogisticsClient;
import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.client.logistics.LogisticsReadClient;
import com.harvest.oms.client.order.OrderDeclareClient;
import com.harvest.oms.domain.logistics.LogisticsChannelAddressDO;
import com.harvest.oms.domain.logistics.LogisticsChannelDO;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.declare.OrderDeclarationDO;
import com.harvest.oms.enums.OrderEventEnum;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import com.harvest.oms.service.order.event.OrderEventPublisher;
import com.harvest.oms.service.order.handler.declare.OrderDeclareHandler;
import com.harvest.oms.service.order.handler.feature.logistics.auth.LogisticsAuthHandler;
import com.harvest.oms.service.order.processor.OrderDeclareProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

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

    @Autowired
    private OrderDeclareClient orderDeclareClient;

    @Autowired(required = false)
    private List<OrderDeclareHandler> orderDeclareHandlers;

    @Autowired
    private List<LogisticsAuthHandler> logisticsAuthHandlers;

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
    public void beforeDeclare(Long companyId, SubmitDeclarationRequest request) {
        OrderInfoDO order = request.getOrder();
        // 渠道地址信息
        List<LogisticsChannelAddressDO> channelAddressList = logisticsReadClient.getChannelAddress(companyId, order.getChannelId());
        request.setChannelAddressList(channelAddressList);

        // 填充对应物流授权
        logisticsAuthHandlers.forEach(handler -> {
            if (handler.match(companyId, request.getLogisticsEnum())) {
                handler.buildAuth(companyId, request);
            }
        });

    }

    @Override
    public DeclarationResponse processDeclare(Long companyId, SubmitDeclarationRequest request) {
        LOGGER.info("OrderDeliveryClientImpl#processDeclare#申报请求: " + JsonUtils.object2Json(request));
        StopWatch stopWatch = new StopWatch();

//        stopWatch.start("预保存申报信息");
//        orderDeclareClient.saveDeclaration(companyId, request);
//        stopWatch.stop();

        stopWatch.start("开始申报");
        DeclarationResponse response = basicLogisticsClient.submitDeclaration(companyId, request);
        stopWatch.stop();

        stopWatch.start("处理申报结果");
        orderDeclareHandlers.forEach(handler -> handler.execute(companyId, request, response));
        stopWatch.stop();

        LOGGER.info("OrderDeliveryClientImpl#processDeclare#申报结果:{}\n{}", JsonUtils.object2Json(response), stopWatch.prettyPrint());
        return response;
    }

    @Override
    public void afterDeclare(Long companyId, SubmitDeclarationRequest request, DeclarationResponse response) {
        // 发布订单订单申报事件
        orderEventPublisher.publishAsync(companyId, request.getOrder().getOrderId(), OrderEventEnum.DECLARE);

    }
}
