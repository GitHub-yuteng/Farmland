package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单导出模版表
 * </p>
 *
 * @author Alodi
 * @since 2022-12-21 18:15:57
 */
@Getter
@Setter
@TableName("farmland_oms_order_template_export")
@ApiModel(value = "FarmlandOmsOrderTemplateExportEntity对象", description = "订单导出模版表")
public class FarmlandOmsOrderTemplateExportEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;
}
