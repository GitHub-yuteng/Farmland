package com.harvest.oms.service.order.handler;

import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:19 PM
 * @Description: 订单申报处理器
 **/
public interface OrderDeclareHandler {

    /**
     * 匹配处理器
     *
     * @param companyId
     * @param response
     * @return
     */
    boolean match(Long companyId, DeclarationResponse response);

    /**
     * 处理申报结果
     *
     * @param companyId
     * @param request
     * @param response
     */
    void process(Long companyId, SubmitDeclarationRequest request, DeclarationResponse response);

    /**
     * 执行
     *
     * @param companyId
     * @param request
     * @param response
     */
    default void execute(Long companyId, SubmitDeclarationRequest request, DeclarationResponse response) {
        if (this.match(companyId, response)) {
            this.process(companyId, request, response);
        }
    }

}
