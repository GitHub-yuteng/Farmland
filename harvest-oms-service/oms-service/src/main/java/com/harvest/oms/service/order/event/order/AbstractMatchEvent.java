package com.harvest.oms.service.order.event.order;

import com.harvest.oms.client.order.OrderReadClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Alodi
 * @Date: 2023/2/21 6:25 PM
 * @Description: TODO
 **/
public abstract class AbstractMatchEvent {

    @Autowired
    protected OrderReadClient orderReadClient;

}
