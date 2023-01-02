package com.harvest.oms.repository.handler.order.section;

import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.handler.order.OrderSectionRepositoryHandler;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 3:28 PM
 * @Description: TODO
 **/
@Component
public class OrderWarehouseSectionHandler implements OrderSectionRepositoryHandler<OrderSimplePO> {

    @Override
    public void save(Long companyId, OrderSimplePO order) {

    }

    @Override
    public void fill(Long companyId, OrderSimplePO order) {

    }

    @Override
    public void batchFill(Long companyId, Collection<OrderSimplePO> orders) {

    }

    @Override
    public void update(Long companyId, Long orderId, OrderSimplePO entity) {

    }
}
