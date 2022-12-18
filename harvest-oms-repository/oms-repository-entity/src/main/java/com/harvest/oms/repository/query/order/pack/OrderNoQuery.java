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

    @ApiModelProperty("订单号")
    private Collection<String> orderNos;

    @ApiModelProperty("发货号")
    private Collection<String> deliveryNos;

    @ApiModelProperty("波次号")
    private Collection<String> waveNos;

    /**
     * ============ ⬇️全匹配查询========== ⬆️模糊查询==================
     */

    @ApiModelProperty("订单号")
    private Collection<String> orderNosAllMatch;

    @ApiModelProperty("发货号")
    private Collection<String> deliveryNosAllMatch;

    @ApiModelProperty("波次号")
    private Collection<String> waveNosAllMatch;

}
