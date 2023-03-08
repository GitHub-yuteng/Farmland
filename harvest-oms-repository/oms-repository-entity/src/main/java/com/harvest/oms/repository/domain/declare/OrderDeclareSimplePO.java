package com.harvest.oms.repository.domain.declare;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/2/16 10:23 PM
 * @Description: TODO
 **/
@Data
public class OrderDeclareSimplePO implements Serializable {

    private static final long serialVersionUID = -9000653848376469888L;

    private Long orderId;

    private Long companyId;

    private Integer status;

    private String lastResponse;

    private List<OrderItemDeclareSimplePO> items;

}
