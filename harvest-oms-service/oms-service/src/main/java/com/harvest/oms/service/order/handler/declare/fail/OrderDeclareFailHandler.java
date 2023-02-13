package com.harvest.oms.service.order.handler.declare.fail;

import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import com.harvest.oms.service.order.handler.OrderDeclareHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/13 10:02 PM
 * @Description: 申报失败处理器
 **/
@Component
public class OrderDeclareFailHandler implements OrderDeclareHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDeclareFailHandler.class);

    @Override
    public boolean match(Long companyId, DeclarationResponse response) {
        return !response.getSuccess();
    }

    @Override
    public void process(Long companyId, SubmitDeclarationRequest request, DeclarationResponse response) {
        LOGGER.info("申报失败处理！");
    }

}
