package com.harvest.oms.domain.order.feature.platform;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/1/29 9:38 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderPlatformFeature<F extends PlatformFeature> extends CompanyId {

    private static final long serialVersionUID = 267043935926491794L;

    private F feature;

}