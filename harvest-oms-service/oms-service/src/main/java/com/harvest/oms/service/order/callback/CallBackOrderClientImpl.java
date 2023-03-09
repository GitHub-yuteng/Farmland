package com.harvest.oms.service.order.callback;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.core.context.SpringHelper;
import com.harvest.oms.client.CallBackOrderClient;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.client.order.OrderAuditClient;
import com.harvest.oms.request.order.audit.SubmitAuditReturnRequest;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/3/6 4:51 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsApplications.Path.CALL_BACK_WMS)
public class CallBackOrderClientImpl implements CallBackOrderClient {

    @Override
    public BatchExecuteResult<String> returnAudit(Long companyId, Collection<Long> orderIds) {
        System.out.println("直接打回审核");
        return SpringHelper.getBean(OrderAuditClient.class).returnAudit(companyId, orderIds);
    }

    @Override
    public BatchExecuteResult<String> returnAuditWithSubmit(Long companyId, Collection<SubmitAuditReturnRequest> requests) {
        return SpringHelper.getBean(OrderAuditClient.class).returnAuditWithSubmit(companyId, requests);
    }

    @Override
    public BatchExecuteResult<String> allocate(Long companyId, Collection<Long> orderIds) {
        return null;
    }

    @Override
    public BatchExecuteResult<String> print(Long companyId, Collection<Long> orderIds) {
        return null;
    }

    @Override
    public BatchExecuteResult<String> collect(Long companyId, Collection<Long> orderIds) {
        return null;
    }

    @Override
    public BatchExecuteResult<String> check(Long companyId, Collection<Long> orderIds) {
        return null;
    }

    @Override
    public BatchExecuteResult<String> pack(Long companyId, Collection<Long> orderIds) {
        return null;
    }
}
