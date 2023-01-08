package com.harvest.basic.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 提供给前端的业务自由配置表
 * </p>
 *
 * @author Alodi
 * @since 2023-01-08 23:11:04
 */
@Getter
@Setter
@TableName("biz_web_config")
@ApiModel(value = "BizWebConfigEntity对象", description = "提供给前端的业务自由配置表")
public class BizWebConfigEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("config_type")
    private Integer configType;

    @TableField("setting")
    private String setting;
}
