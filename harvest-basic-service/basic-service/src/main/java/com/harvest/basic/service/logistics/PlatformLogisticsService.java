package com.harvest.basic.service.logistics;

import com.harvest.basic.client.logistics.PlatformLogisticsClient;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 12:18 PM
 * @Description: TODO
 **/
@Component
public class PlatformLogisticsService extends AbstractPlatformLogisticsService implements PlatformLogisticsClient {

    @Override
    public void submitDeclaration(Long companyId, SubmitDeclarationRequest request) {
        super.call(request.getLogistics().getIndex()).submitDeclaration(companyId, request);
    }

    @Override
    public void print(Long companyId) {
        super.call(3).print(companyId);
    }
}
