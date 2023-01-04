package com.harvest.oms.repository.handler.order.section;

import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.handler.order.OrderSectionRepositoryHandler;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 3:28 PM
 * @Description: 订单仓库基本信息
 **/
@Component
public class OrderWarehouseSectionHandler implements OrderSectionRepositoryHandler<OrderSimplePO> {

    @Override
    public void save(Long companyId, OrderSimplePO order) {

    }

    @Override
    public void fill(Long companyId, OrderSimplePO order) {

    }

    /**
     * 只查询仓库基础信息, 不含业务信息, 为丰富查询提供订单仓库信息简要
     *
     * @param companyId 公司id
     * @param orders    订单集合
     */
    @Override
    public void batchFill(Long companyId, Collection<OrderSimplePO> orders) {

    }

    @Override
    public void update(Long companyId, Long orderId, OrderSimplePO entity) {

    }
}
