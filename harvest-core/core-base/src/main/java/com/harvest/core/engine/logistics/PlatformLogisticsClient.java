package com.harvest.core.engine.logistics;

import com.harvest.core.constants.GlobalMacroDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2023/2/4 1:36 PM
 * @Description: TODO
 **/
public interface PlatformLogisticsClient extends GlobalMacroDefinition {

    @PostMapping("/submitDeclaration")
    void submitDeclaration(@RequestParam(COMPANY_ID) long companyId);

}
