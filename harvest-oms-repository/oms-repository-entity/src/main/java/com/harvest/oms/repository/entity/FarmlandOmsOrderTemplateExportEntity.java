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
 * @since 2022-12-19 13:54:53
 */
@Getter
@Setter
@TableName("farmland_oms_order_template_export")
@ApiModel(value = "FarmlandOmsOrderTemplateExportEntity对象", description = "订单导出模版表")
public class FarmlandOmsOrderTemplateExportEntity {

    @TableId("id")
    private Long id;
}
