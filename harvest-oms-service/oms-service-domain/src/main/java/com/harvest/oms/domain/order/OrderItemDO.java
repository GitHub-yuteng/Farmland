package com.harvest.oms.domain.order;

import com.harvest.oms.repository.domain.order.simple.OrderItemSimplePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2022/12/29 2:51 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderItemDO extends OrderItemSimplePO {

    private static final long serialVersionUID = -4156040614397036930L;


}
