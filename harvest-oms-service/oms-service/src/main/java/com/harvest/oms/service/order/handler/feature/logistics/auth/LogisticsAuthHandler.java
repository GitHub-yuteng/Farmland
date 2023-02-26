package com.harvest.oms.service.order.handler.feature.logistics.auth;

import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;

/**
 * @Author: Alodi
 * @Date: 2023/2/26 11:01 AM
 * @Description: TODO
 **/
public interface LogisticsAuthHandler {

    default boolean match(Long companyId, LogisticsEnum logisticsEnum) {
        return false;
    }

    /**
     * @param companyId
     * @param request
     */
    void buildAuth(Long companyId, SubmitDeclarationRequest request);

}
