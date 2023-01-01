package com.harvest.oms.repository.domain.order.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 1:30 AM
 * @Description: TODO
 **/
@Data
public class OrderTag implements Serializable {

    private static final long serialVersionUID = -4601884852299102212L;

    @ApiModelProperty("订单标记Id")
    private Long tagId;

    @ApiModelProperty("订单标记值")
    private Integer tagValue;

    @ApiModelProperty("订单标记扩展信息")
    private String simpleExtension;

    @ApiModelProperty("额外信息")
    private Object extension;

}
