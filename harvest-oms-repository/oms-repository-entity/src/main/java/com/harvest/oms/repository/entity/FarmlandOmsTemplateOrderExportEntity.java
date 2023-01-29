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
 * @since 2023-01-29 14:52:42
 */
@Getter
@Setter
@TableName("farmland_oms_template_order_export")
@ApiModel(value = "FarmlandOmsTemplateOrderExportEntity对象", description = "订单导出模版表")
public class FarmlandOmsTemplateOrderExportEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;
}
