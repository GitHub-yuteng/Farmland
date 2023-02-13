package com.harvest.oms.repository.domain.logistics;

import com.harvest.core.domain.CompanyId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/1/30 8:48 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LogisticsCore extends CompanyId {

    private static final long serialVersionUID = -8986055227259428619L;

    private Long logisticsId;

    private String logisticsCode;

    private String logistics;

    private Integer status;

}
