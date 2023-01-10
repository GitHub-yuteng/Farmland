package com.harvest.core.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/10 8:46 PM
 * @Description: 项目公司Id
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyId implements Serializable {

    private static final long serialVersionUID = -3642631455723326968L;

    /**
     * 公司Id
     */
    @ApiModelProperty("公司Id")
    public Long companyId;

}
