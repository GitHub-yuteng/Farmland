package com.harvest.oms.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/10 8:46 PM
 * @Description: 项目公司Id
 **/
@Data
public class HarvestCompanyId implements Serializable {

    /**
     * 公司Id
     */
    public long companyId;

}
