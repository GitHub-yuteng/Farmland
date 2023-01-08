package com.harvest.basic.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alodi
 * @since 2023-01-08 23:11:03
 */
@Getter
@Setter
@TableName("biz_country")
@ApiModel(value = "BizCountryEntity对象", description = "")
public class BizCountryEntity {

    @TableField("id")
    private Long id;

    @TableField("country_code")
    private String countryCode;

    @TableField("country_cn")
    private String countryCn;

    @TableField("country_en")
    private String countryEn;
}
