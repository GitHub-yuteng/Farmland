package com.harvest.oms.service.order.processor;

import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.core.annotation.BizLog;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 11:21 PM
 * @Description: 订单交运模版处理器, 业务流程具像化
 **/
public interface OrderDeclareProcessor {

    /**
     * 交运申报前置校验-报错处理
     *
     * @param companyId
     * @param request
     * @return
     */
    void check(Long companyId, SubmitDeclarationRequest request);

    /**
     * 交运申报前置处理-处理参数等
     *
     * @param companyId
     * @param request
     * @return
     */
    void beforeDeclare(Long companyId, SubmitDeclarationRequest request);

    /**
     * 业务处理过程
     *
     * @param companyId
     */
    DeclarationResponse processDeclare(Long companyId, SubmitDeclarationRequest request);

    /**
     * 交运申报后置处理
     *
     * @param companyId
     * @param response
     */
    void afterDeclare(Long companyId, SubmitDeclarationRequest request, DeclarationResponse response);

    /**
     * 执行业务流程
     *
     * @param companyId
     * @param request
     */
    default void execute(Long companyId, SubmitDeclarationRequest request) {
        this.check(companyId, request);
        this.beforeDeclare(companyId, request);
        DeclarationResponse response = this.processDeclare(companyId, request);
        this.afterDeclare(companyId, request, response);
    }

}
