package com.harvest.basic.client.logistics;

import com.harvest.basic.client.constants.HarvestBasicApplications;
import com.harvest.basic.domain.logistics.DeclarationDataFile;
import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.basic.service.logistics.PlatformLogisticsService;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

/**
 * @Author: Alodi
 * @Date: 2023/2/3 2:48 PM
 * @Description: 物流驱动
 **/
@HarvestService(path = HarvestBasicApplications.Path.LOGISTICS)
public class BasicLogisticsClientImpl implements BasicLogisticsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicLogisticsClientImpl.class);

    @Autowired
    private PlatformLogisticsService platformLogisticsService;

    /**
     * Basic 驱动 交运申报 -> 获取面单
     *
     * @param companyId
     * @param request
     */
    @Override
    public DeclarationResponse submitDeclaration(Long companyId, SubmitDeclarationRequest request) {

        StopWatch stopWatch = new StopWatch("订单申报监控");

        stopWatch.start("提交申报");
        DeclarationResponse response = platformLogisticsService.submitDeclaration(companyId, request);
        stopWatch.stop();

        stopWatch.start("获取面单");
        DeclarationDataFile file = platformLogisticsService.print(companyId, request);
        response.setFile(file);
        stopWatch.stop();

        LOGGER.info("BasicLogisticsClientImpl#submitDeclaration, \n{}", stopWatch.prettyPrint());

        return response;
    }

}
