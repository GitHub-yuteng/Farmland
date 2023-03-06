package com.harvest.wms.repository.client.callback.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.batch.BatchExecuteResult;
import com.harvest.oms.client.order.OrderAuditClient;
import com.harvest.oms.request.order.audit.SubmitAuditReturnRequest;
import com.harvest.wms.repository.constants.HarvestWmsApplications;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/3/6 4:51 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestWmsApplications.Path.CALL_BACK_ORDER)
public class CallBackOrderClientImpl implements CallBackOrderClient {

    @Autowired
    private OrderAuditClient orderAuditClient;

    @Override
    public BatchExecuteResult<String> returnAudit(Long companyId, Collection<Long> orderIds) {
        return orderAuditClient.returnAudit(companyId, orderIds);
    }

    @Override
    public BatchExecuteResult<String> returnAuditWithSubmit(Long companyId, Collection<SubmitAuditReturnRequest> requests) {
        return orderAuditClient.returnAuditWithSubmit(companyId, requests);
    }
}
