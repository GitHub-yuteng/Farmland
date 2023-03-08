package com.harvest.oms.repository.domain.declare;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 10:23 PM
 * @Description: TODO
 **/
@Data
public class OrderItemDeclareSimplePO implements Serializable {

    private static final long serialVersionUID = -1023164652734027451L;

    private Long orderItemId;

    private Long companyId;

    private Long orderId;

    private Long spuId;

    private Long skuId;

    private BigDecimal quantity;
}
