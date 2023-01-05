package com.harvest.oms.service.order.check;

import com.harvest.oms.repository.query.order.PageOrderConditionQuery;

/**
 * @Author: Alodi
 * @Date: 2023/1/5 10:31 AM
 * @Description: 权限校验处理器
 **/
@FunctionalInterface
public interface OrderPermissionsVerifier {

    void check(Long companyId, PageOrderConditionQuery condition);

}
