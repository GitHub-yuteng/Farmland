package com.harvest.oms.repository.query.order.pack;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 5:14 PM
 * @Description: TODO
 **/
@Data
public class OrderAddressQuery {

    @ApiModelProperty("邮政编码")
    private String postalCode;
}
