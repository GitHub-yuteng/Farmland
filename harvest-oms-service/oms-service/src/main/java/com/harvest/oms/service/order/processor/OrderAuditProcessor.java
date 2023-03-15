package com.harvest.oms.service.order.processor;

import com.harvest.oms.domain.order.audit.OrderAuditTransferDTO;
import com.harvest.oms.request.order.audit.SubmitAuditRequest;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 11:21 PM
 * @Description: 订单审核模版处理器, 业务流程具像化
 **/
public interface OrderAuditProcessor {

    /**
     * 前置校验
     *
     * @param companyId
     * @param request
     */
    void check(Long companyId, SubmitAuditRequest request);

    /**
     * 订单单审核前置处理
     *
     * @param companyId
     * @param request
     * @return
     */
    OrderAuditTransferDTO beforeAudit(Long companyId, SubmitAuditRequest request);

    /**
     * 业务处理过程
     *
     * @param companyId
     * @param transfer
     */
    void processAudit(Long companyId, SubmitAuditRequest request, OrderAuditTransferDTO transfer);

    /**
     * 后置处理
     *
     * @param companyId
     */
    void afterAudit(Long companyId, SubmitAuditRequest request);

    /**
     * 日志
     *
     * @param companyId
     * @param request
     */
    void log(Long companyId, SubmitAuditRequest request);

    /**
     * 执行业务流程
     *
     * @param companyId
     * @param request
     */
    default void execute(Long companyId, SubmitAuditRequest request) {
        this.check(companyId, request);
        OrderAuditTransferDTO transfer = this.beforeAudit(companyId, request);
        this.processAudit(companyId, request, transfer);
        this.afterAudit(companyId, request);
        this.log(companyId, request);
    }

}
