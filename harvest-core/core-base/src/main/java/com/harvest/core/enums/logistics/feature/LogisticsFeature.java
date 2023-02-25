package com.harvest.core.enums.logistics.feature;

import com.harvest.core.domain.CompanyId;
import com.harvest.core.enums.logistics.LogisticsEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/25 3:39 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class LogisticsFeature extends CompanyId {

    private static final long serialVersionUID = 2753463389440837044L;

    private LogisticsEnum logisticsEnum;


}