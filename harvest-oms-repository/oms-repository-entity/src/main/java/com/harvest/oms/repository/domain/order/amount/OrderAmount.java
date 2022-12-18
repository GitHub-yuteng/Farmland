package com.harvest.oms.repository.domain.order.amount;

import com.harvest.oms.repository.domain.order.amount.cost.OrderCost;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:27 AM
 * @Description:
 **/
@Data
public class OrderAmount {



    private OrderCost orderCost;

}
