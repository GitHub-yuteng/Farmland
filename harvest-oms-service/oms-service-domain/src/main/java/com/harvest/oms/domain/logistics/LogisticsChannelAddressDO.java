package com.harvest.oms.domain.logistics;

import com.harvest.core.domain.address.HarvestAddress;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 10:15 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LogisticsChannelAddressDO extends HarvestAddress {

    private static final long serialVersionUID = -3225129685192443595L;

    private Long shopId;

}
