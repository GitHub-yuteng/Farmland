package com.harvest.oms.repository.convert.optimize.filter;

import com.harvest.oms.repository.convert.OrderOptimizeQueryConvertor;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: Alodi
 * @Date: 2023/1/5 10:10 AM
 * @Description: 地址模糊查询优化
 **/
@Component
public class OrderAddressFilterQueryConvertor implements OrderOptimizeQueryConvertor {

    @Override
    public Collection<Long> convert(Long companyId, PageOrderConditionQuery condition, Map<String, Object> params) {
        return null;
    }
}
