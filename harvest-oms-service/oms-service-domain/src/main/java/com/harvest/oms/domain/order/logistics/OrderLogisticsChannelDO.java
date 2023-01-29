package com.harvest.oms.domain.order.logistics;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 10:15 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderLogisticsChannelDO extends OrderLogisticsDO {

    private static final long serialVersionUID = -3225129685192443595L;

    private Long channelId;

}
