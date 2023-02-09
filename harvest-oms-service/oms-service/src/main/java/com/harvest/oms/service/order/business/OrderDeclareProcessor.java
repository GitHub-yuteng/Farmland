package com.harvest.oms.service.order.business;

import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 11:21 PM
 * @Description: 订单交运模版处理器, 业务流程具像化
 **/
public interface OrderDeclareProcessor {

    /**
     * 交运申报前置校验
     *
     * @param companyId
     * @param request
     * @return
     */
    void check(Long companyId, SubmitDeclarationRequest request);

    /**
     * 交运申报前置处理
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
    void processDeclare(Long companyId, SubmitDeclarationRequest request);

    /**
     * 交运申报后置处理
     *
     * @param companyId
     */
    void afterDeclare(Long companyId, SubmitDeclarationRequest request);

    /**
     * 执行业务流程
     *
     * @param companyId
     * @param request
     */
    default void executeDeclare(Long companyId, SubmitDeclarationRequest request) {
        this.beforeDeclare(companyId, request);
        this.processDeclare(companyId, request);
        this.afterDeclare(companyId, request);
    }

}
