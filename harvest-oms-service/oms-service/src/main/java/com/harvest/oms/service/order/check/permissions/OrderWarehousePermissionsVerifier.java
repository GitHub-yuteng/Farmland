package com.harvest.oms.service.order.check.permissions;

import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.service.order.check.OrderPermissionsVerifier;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/1/5 11:12 AM
 * @Description: TODO
 **/
@Component
public class OrderWarehousePermissionsVerifier implements OrderPermissionsVerifier {

    @Override
    public void check(Long companyId, PageOrderConditionQuery condition) {

    }
}
