package com.harvest.oms.repository.handler.order.section;

import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.handler.order.OrderSectionRepositoryHandler;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 2:57 PM
 * @Description: TODO
 **/
@Component
public class OrderTagSectionHandler implements OrderSectionRepositoryHandler<OrderSimplePO> {


    @Override
    public void save(long companyId, OrderSimplePO order) {

    }

    @Override
    public void fill(long companyId, OrderSimplePO order) {

    }

    @Override
    public void batchFill(long companyId, Collection<OrderSimplePO> orders) {

    }

    @Override
    public void update(long companyId, long orderId, OrderSimplePO entity) {

    }
}
