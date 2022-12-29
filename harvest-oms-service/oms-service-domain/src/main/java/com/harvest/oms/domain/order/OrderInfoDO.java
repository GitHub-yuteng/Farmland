package com.harvest.oms.domain.order;

import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:25 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderInfoDO extends OrderSimplePO {

    private static final long serialVersionUID = 2547095981099704006L;

    private Collection<OrderItemDO> orderItems;

}
