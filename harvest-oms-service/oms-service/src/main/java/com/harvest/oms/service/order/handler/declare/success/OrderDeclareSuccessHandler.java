package com.harvest.oms.service.order.handler.declare.success;

import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.oms.client.order.OrderWriteClient;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import com.harvest.oms.service.order.handler.OrderDeclareHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/13 10:02 PM
 * @Description: 申报成功处理器
 **/
@Component
public class OrderDeclareSuccessHandler implements OrderDeclareHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderDeclareSuccessHandler.class);

    @Autowired
    private OrderWriteClient orderWriteClient;

    @Override
    public boolean match(Long companyId, DeclarationResponse response) {
        return response.getSuccess();
    }

    @Override
    public void process(Long companyId, SubmitDeclarationRequest request, DeclarationResponse response) {
        this.updateDeclare(companyId, request, response);
    }

    /**
     * 更新订单申报状态
     *
     * @param companyId
     * @param request
     * @param response
     */
    private void updateDeclare(Long companyId, SubmitDeclarationRequest request, DeclarationResponse response) {
        OrderInfoDO _order = request.getOrder();
        OrderInfoDO order = new OrderInfoDO();
        order.setOrderId(_order.getOrderId());
        order.setDeliveryNo(response.getDeliveryNo());
        order.setWaitDeclare(true);
        orderWriteClient.updateDeclare(companyId, order);
    }

}
