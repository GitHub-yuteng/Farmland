package com.harvest.oms.service.order.verifier;

import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Alodi
 * @Date: 2023/1/5 11:12 AM
 * @Description: 店铺权限校验
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class OrderUserPermissionsVerifier implements OrderPermissionsVerifier {

    @Override
    public void check(Long companyId, PageOrderConditionQuery condition) {

    }
}
