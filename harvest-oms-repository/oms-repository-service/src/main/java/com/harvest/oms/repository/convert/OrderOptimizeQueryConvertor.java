package com.harvest.oms.repository.convert;

import com.harvest.core.utils.FieldUtils;
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
     * 1、return Collections.singletonList(0L); 作为查询标记位 如果查询为空则返回 0L
     * 2、如果返回为 Collections.emptyList(); 则代表该 skip convert
     * 3、如果返回具体 orderIds; 则处理 orderIds 交集处理
     *
     * @param companyId
     * @param condition
     * @param params
     * @return
     */
    default boolean process(Long companyId, PageOrderConditionQuery condition, Map<String, Object> params) {
        Collection<Long> orderIds = this.convert(companyId, condition, params);
        if (CollectionUtils.isNotEmpty(orderIds) && orderIds.contains(0L)) {
            params.put(FieldUtils.getFieldName(PageOrderConditionQuery::getOrderIds), orderIds);
            return true;
        }
        List<Long> paramOrderIds = (List<Long>) params.get(FieldUtils.getFieldName(PageOrderConditionQuery::getOrderIds));
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
            params.put(FieldUtils.getFieldName(PageOrderConditionQuery::getOrderIds), orderIds);
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
        params.put(FieldUtils.getFieldName(PageOrderConditionQuery::getOrderIds), orderIds);
    }

}
