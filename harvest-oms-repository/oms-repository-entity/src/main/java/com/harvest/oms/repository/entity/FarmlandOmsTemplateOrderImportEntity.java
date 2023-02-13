package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单导入模版表
 * </p>
 *
 * @author Alodi
 * @since 2023-02-13 15:05:22
 */
@Getter
@Setter
@TableName("farmland_oms_template_order_import")
@ApiModel(value = "FarmlandOmsTemplateOrderImportEntity对象", description = "订单导入模版表")
public class FarmlandOmsTemplateOrderImportEntity {

    @ApiModelProperty("primary key")
    @TableId("id")
    private Long id;
}
