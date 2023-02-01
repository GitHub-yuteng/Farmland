package com.harvest.oms.domain.order.logistics;

import com.harvest.oms.repository.domain.logistics.simple.LogisticsSimplePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 4:01 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderLogisticsDO extends LogisticsSimplePO {

    private static final long serialVersionUID = 3584012581034612621L;


}
