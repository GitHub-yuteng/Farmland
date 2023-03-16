package com.harvest.oms.repository.client.order;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2023/3/16 11:11 AM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestOmsRepositoryApplications.SERVICE_NAME, path = HarvestOmsRepositoryApplications.Path.ORDER_OPERATION_LOG)
public interface OrderOperationLogRepositoryClient {

    @ApiOperation("查询报关信息")
    @PostMapping("/batchStore")
    void batchStore(@RequestBody Collection<OrderOperationLog> logs);

}
