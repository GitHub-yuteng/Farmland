package com.harvest.oms.service.order.handler.feature.logistics.auth;

import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.core.utils.JsonUtils;
import com.harvest.oms.client.logistics.LogisticsReadClient;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/25 10:14 PM
 * @Description: TODO
 **/
@Component
public class LogisticsAuth_Cainiao_Handler extends AbstractLogisticsAuthHandler {

    @Autowired
    private LogisticsReadClient logisticsReadClient;

    @Override
    public boolean match(Long companyId, LogisticsEnum logisticsEnum) {
        return LogisticsEnum.CAINIAO.equals(logisticsEnum);
    }

    @Override
    public void buildAuth(Long companyId, SubmitDeclarationRequest request) {
        LogisticsEnum logisticsEnum = request.getLogisticsEnum();
        String authorization = logisticsReadClient.getAuthorization(companyId, logisticsEnum);
        Class<?> authClazz = LogisticsEnum.getAuthByType(logisticsEnum.getType());
        request.setAuthorization(JsonUtils.json2Object(authorization, authClazz));
    }

}

