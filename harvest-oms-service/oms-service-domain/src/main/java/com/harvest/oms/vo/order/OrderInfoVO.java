package com.harvest.oms.vo.order;

import com.harvest.oms.domain.order.OrderInfoDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:51 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderInfoVO extends OrderInfoDO implements Serializable {

    private static final long serialVersionUID = -1629744239248440566L;

}
