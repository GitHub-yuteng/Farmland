package com.harvest.oms.request.order.declare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harvest.oms.domain.order.OrderInfoDO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2023/2/5 4:13 PM
 * @Description: TODO
 **/
@Data
public class SubmitDeclarationRequest implements Serializable {

    private static final long serialVersionUID = -3113055454947802845L;

    private Long orderId;

    @JsonIgnore
    private OrderInfoDO order;

}
