package com.harvest.core.engine.logistics;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: Alodi
 * @Date: 2023/2/4 1:36 PM
 * @Description: TODO
 **/
public interface PlatformLogisticsClient {

    @PostMapping("/submitDeclaration")
    void submitDeclaration(long companyId);

}
