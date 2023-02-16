package com.harvest.oms.repository.client.order;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.oms.repository.constants.HarvestOmsRepositoryApplications;
import com.harvest.oms.repository.domain.declare.OrderDeclareSimplePO;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:03 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestOmsRepositoryApplications.Path.ORDER_DECLARE)
public class OrderDeclareRepositoryClientImpl implements OrderDeclareRepositoryClient {

    @Override
    public Collection<OrderDeclareSimplePO> listDeclaration(Long companyId, List<Long> orderIds) {
        return null;
    }

}
