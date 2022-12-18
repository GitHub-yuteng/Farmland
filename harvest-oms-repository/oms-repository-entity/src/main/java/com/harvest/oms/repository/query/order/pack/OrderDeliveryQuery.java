package com.harvest.oms.repository.query.order.pack;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 4:35 PM
 * @Description: TODO
 **/
@Data
public class OrderDeliveryQuery implements Serializable {

    private static final long serialVersionUID = -7488206408579177336L;

    @ApiModelProperty("交运状态")
    private Integer declareStatus;

    @ApiModelProperty("承运商")
    private Collection<Long> carrierIds;

    @ApiModelProperty("渠道")
    private Collection<Long> channelIds;

}
