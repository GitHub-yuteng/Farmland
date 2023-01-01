package com.harvest.oms.repository.handler.order;

import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 1:25 PM
 * @Description: TODO
 **/
public interface OrderSectionRepositoryHandler<T> {

    /**
     * 保存订单
     *
     * @param companyId 公司id
     * @param order     订单
     */
    void save(long companyId, OrderSimplePO order);

    /**
     * 补充订单信息
     *
     * @param companyId 公司id
     * @param order     订单
     */
    void fill(long companyId, OrderSimplePO order);

    /**
     * 批量补充
     *
     * @param companyId 公司id
     * @param orders    订单集合
     */
    void batchFill(long companyId, Collection<OrderSimplePO> orders);

    /**
     * 更新
     *
     * @param companyId 公司id
     * @param orderId   订单id
     * @param entity    订单相关实体
     */
    void update(long companyId, long orderId, T entity);
}
