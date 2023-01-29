package com.harvest.oms.repository.domain.order;

import com.harvest.core.domain.OperationType;
import com.harvest.core.enums.oms.OrderSourceEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2022/12/10 8:56 PM
 * @Description: 订单核心领域模型
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCore extends OperationType {

    private static final long serialVersionUID = -2926021609995331681L;

    @ApiModelProperty("订单Id")
    private Long orderId;

    @ApiModelProperty("店铺Id")
    private Long shopId;

    @ApiModelProperty("订单来源")
    private OrderSourceEnum orderSource;

}
