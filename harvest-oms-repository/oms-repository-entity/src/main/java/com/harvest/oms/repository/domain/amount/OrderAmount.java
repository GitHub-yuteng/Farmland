package com.harvest.oms.repository.domain.amount;

import com.harvest.oms.repository.domain.amount.cost.OrderCost;
import com.harvest.oms.repository.domain.amount.transaction.OrderTransactionAmount;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:27 AM
 * @Description:
 **/
@Data
public class OrderAmount {

    private OrderTransactionAmount transactionAmount;

    private OrderCost orderCost;

}
