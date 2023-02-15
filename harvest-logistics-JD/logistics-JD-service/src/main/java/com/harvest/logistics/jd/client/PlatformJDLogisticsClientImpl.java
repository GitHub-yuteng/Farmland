package com.harvest.logistics.jd.client;

import com.harvest.basic.domain.logistics.DeclarationDataFile;
import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.logistics.jd.HarvestJDLogisticsApplications;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Alodi
 * @Date: 2023/2/4 2:27 PM
 * @Description: 菜鸟物流平台
 **/
@HarvestService(path = HarvestJDLogisticsApplications.SERVICE_PATH)
public class PlatformJDLogisticsClientImpl implements PlatformJDLogisticsClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(PlatformJDLogisticsClientImpl.class);

    @Override
    public void getToken(Long companyId) {

    }

    @Override
    public void refreshToken(Long companyId) {

    }

    @Override
    public void validAccount(Long companyId) {

    }

    @Override
    public DeclarationResponse submitDeclaration(Long companyId, SubmitDeclarationRequest request) {
        return null;
    }

    @Override
    public void getDeliveryNo(Long companyId, SubmitDeclarationRequest request) {

    }

    @Override
    public DeclarationDataFile print(Long companyId, SubmitDeclarationRequest request) {
        return null;
    }

    @Override
    public void cancelDeclaration(Long companyId, SubmitDeclarationRequest request) {

    }

    @Override
    public void queryPickTime(Long companyId) {

    }
}
