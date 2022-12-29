package com.harvest.oms.repository.domain.order.base;

import com.harvest.core.domain.address.HarvestAddress;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 2:05 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderAddress extends HarvestAddress {

    private static final long serialVersionUID = -5650677554760895170L;

    private Long orderId;

}
