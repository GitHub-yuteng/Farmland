package com.harvest.basic.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 公司配置开关表
 * </p>
 *
 * @author Alodi
 * @since 2023-01-08 23:11:03
 */
@Getter
@Setter
@TableName("biz_company_switch")
@ApiModel(value = "BizCompanySwitchEntity对象", description = "公司配置开关表")
public class BizCompanySwitchEntity {

    @TableId("id")
    private Long id;
}
