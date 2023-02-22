package com.harvest.oms.repository.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.entity.FarmlandOmsOrderEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:13 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestOmsRepositoryApplications.SERVICE_NAME, path = HarvestOmsRepositoryApplications.Path.ORDER_WRITE)
public interface OrderWriteRepositoryClient extends GlobalMacroDefinition {

    @ApiOperation("保存订单")
    @PostMapping("/insert")
    void insert(@RequestParam(COMPANY_ID) Long companyId, @RequestBody OrderSimplePO orderSimplePO);

    @ApiOperation("更新申报")
    @PostMapping("/updateDeclare")
    void updateDeclare(@RequestParam(COMPANY_ID) Long companyId, @RequestBody FarmlandOmsOrderEntity entity);
}
