package com.harvest.oms.domain.order.feature.company;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/1/4 3:39 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyFeature_Farmland extends CompanyFeature {

    private static final long serialVersionUID = -1793977241391913644L;

    private String name;
}