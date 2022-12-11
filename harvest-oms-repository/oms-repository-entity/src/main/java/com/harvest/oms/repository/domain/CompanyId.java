package com.harvest.oms.repository.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/10 8:46 PM
 * @Description: 项目公司Id
 **/
@Data
public class CompanyId implements Serializable {

    private static final long serialVersionUID = -3642631455723326968L;

    /**
     * 公司Id
     */
    public Long companyId;

}
