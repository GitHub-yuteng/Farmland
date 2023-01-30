package com.harvest.oms.domain.order.company;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/1/4 3:39 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class CompanyFeature extends CompanyId {

    private static final long serialVersionUID = -1793977241391913644L;

}