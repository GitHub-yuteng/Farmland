package com.harvest.oms.repository.query.order.pack;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/18 4:13 PM
 * @Description: 订单级别单号查询
 **/
@Data
public class OrderNoQuery {

    /**
     * ================== 精准匹配查询 ==================
     */

    @ApiModelProperty("订单号")
    private Collection<String> orderNos;

    @ApiModelProperty("运单号")
    private Collection<String> deliveryNos;

    @ApiModelProperty("波次号")
    private Collection<String> waveNos;

    /**
     * ================== 模糊匹配查询 ==================
     */

    @ApiModelProperty("订单号")
    private Collection<String> orderNosFilter;

    @ApiModelProperty("发货号")
    private Collection<String> deliveryNosFilter;

    @ApiModelProperty("波次号")
    private Collection<String> waveNosFilter;

}
