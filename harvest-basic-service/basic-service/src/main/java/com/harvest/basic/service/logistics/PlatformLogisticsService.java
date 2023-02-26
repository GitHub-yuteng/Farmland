package com.harvest.basic.service.logistics;

import com.harvest.basic.client.logistics.PlatformLogisticsClient;
import com.harvest.basic.domain.logistics.DeclarationDataFile;
import com.harvest.basic.domain.logistics.DeclarationResponse;
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
    public void getToken(Long companyId) {
        super.call(3).getToken(companyId);
    }

    @Override
    public void refreshToken(Long companyId) {
        super.call(3).refreshToken(companyId);
    }

    @Override
    public void validAccount(Long companyId) {
        super.call(3).validAccount(companyId);
    }

    @Override
    public DeclarationResponse submitDeclaration(Long companyId, SubmitDeclarationRequest request) {
        return super.call(request.getLogisticsEnum().getType()).submitDeclaration(companyId, request);
    }

    @Override
    public void getDeliveryNo(Long companyId, SubmitDeclarationRequest request) {
        super.call(request.getLogisticsEnum().getType()).getDeliveryNo(companyId, request);
    }

    @Override
    public DeclarationDataFile print(Long companyId, SubmitDeclarationRequest request) {
        return super.call(request.getLogisticsEnum().getType()).print(companyId, request);
    }

    @Override
    public void cancelDeclaration(Long companyId, SubmitDeclarationRequest request) {
        super.call(3).cancelDeclaration(companyId, request);
    }

    @Override
    public void queryPickTime(Long companyId) {
        super.call(3).queryPickTime(companyId);
    }
}
