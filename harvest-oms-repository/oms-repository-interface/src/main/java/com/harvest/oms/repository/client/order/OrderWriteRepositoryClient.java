package com.harvest.oms.repository.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.entity.FarmlandOmsOrderEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:13 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestOmsRepositoryApplications.SERVICE_NAME, path = HarvestOmsRepositoryApplications.Path.ORDER_WRITE)
public interface OrderWriteRepositoryClient extends GlobalMacroDefinition {

    @ApiOperation("更新申报")
    @PostMapping("/updateDeclare")
    void updateDeclare(Long companyId, FarmlandOmsOrderEntity entity);

}
