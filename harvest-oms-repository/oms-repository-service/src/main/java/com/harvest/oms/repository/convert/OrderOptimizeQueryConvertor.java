package com.harvest.oms.repository.convert;

import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/4 5:59 PM
 * @Description: 拆分优化查询处理器
 **/
@FunctionalInterface
public interface OrderOptimizeQueryConvertor {

    /**
     * 订单查询参数转换。主要做一些参数预处理，目标是提交订单查询效率
     *
     * @param companyId 公司id
     * @param condition 前端传的原始查询条件
     * @param params    转换后的查询参数
     */
    Collection<Long> convert(Long companyId, PageOrderConditionQuery condition, Map<String, Object> params);

    /**
     * 处理查询订单Ids
     *
     * @param companyId
     * @param condition
     * @param params
     * @return
     */
    default boolean precess(Long companyId, PageOrderConditionQuery condition, Map<String, Object> params) {
        Collection<Long> orderIds = this.convert(companyId, condition, params);
        if (orderIds.contains(0L)) {
            return true;
        }
        List<Long> paramOrderIds = (List<Long>) params.get("orderIds");
        this.handleOrderIds(paramOrderIds, orderIds, params);
        return false;
    }

    /**
     * 处理拆分处理器查询出的Ids
     *
     * @param paramOrderIds 第一次为参数Ids，后续为拆分查询处理器处理过的Ids
     * @param orderIds
     * @param params
     */
    default void handleOrderIds(List<Long> paramOrderIds, Collection<Long> orderIds, Map<String, Object> params) {
        if (CollectionUtils.isEmpty(paramOrderIds)) {
            params.put("orderIds", orderIds);
            return;
        }

        if (CollectionUtils.isEmpty(orderIds)) {
            orderIds = paramOrderIds;
        } else {
            orderIds = orderIds.stream().filter(paramOrderIds::contains).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(orderIds)) {
                orderIds = Collections.singletonList(0L);
            }
        }
        params.put("orderIds", orderIds);
    }

}
