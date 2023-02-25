package com.harvest.oms.domain.order.feature.logistics;

import com.harvest.core.domain.CompanyId;
import com.harvest.core.enums.logistics.feature.LogisticsFeature;
import com.harvest.oms.domain.order.feature.platform.PlatformFeature;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 9:38 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderLogisticsFeature<F extends LogisticsFeature> extends CompanyId {

    private static final long serialVersionUID = 267043935926491794L;

    private F feature;

}