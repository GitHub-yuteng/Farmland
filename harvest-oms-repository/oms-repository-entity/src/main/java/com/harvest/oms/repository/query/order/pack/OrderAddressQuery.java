package com.harvest.oms.repository.query.order.pack;

import com.harvest.core.domain.address.HarvestAddress;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 5:14 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderAddressQuery extends HarvestAddress {

    private static final long serialVersionUID = 6771380426807520453L;

}
