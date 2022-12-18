package com.harvest.oms.repository.domain.order.pack;

import com.harvest.core.domain.address.HarvestAddress;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 2:05 AM
 * @Description: TODO
 **/
public class OrderAddress extends HarvestAddress {

    private static final long serialVersionUID = -5650677554760895170L;

    @ApiModelProperty("地址Id")
    private Long id;
}
