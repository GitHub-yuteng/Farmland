package com.harvest.oms.service.order.verifier;

import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/1/5 11:12 AM
 * @Description: 仓库权限校验
 **/
@Component
public class OrderWarehousePermissionsVerifier implements OrderPermissionsVerifier {

    @Override
    public void check(Long companyId, PageOrderConditionQuery condition) {

    }
}
