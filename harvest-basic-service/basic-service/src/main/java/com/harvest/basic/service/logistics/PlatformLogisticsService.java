package com.harvest.basic.service.logistics;

import com.harvest.core.engine.logistics.AbstractPlatformLogisticsService;
import com.harvest.core.engine.logistics.PlatformLogisticsClient;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 12:18 PM
 * @Description: TODO
 **/
@Component
public class PlatformLogisticsService extends AbstractPlatformLogisticsService implements PlatformLogisticsClient {

    @Override
    public void submitDeclaration(long companyId) {
        super.call(3).submitDeclaration(companyId);
    }

}
