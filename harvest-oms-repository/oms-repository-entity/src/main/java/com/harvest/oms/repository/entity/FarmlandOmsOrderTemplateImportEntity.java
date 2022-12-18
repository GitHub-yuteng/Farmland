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
 * @since 2022-12-18 21:08:53
 */
@Getter
@Setter
@TableName("farmland_oms_order_template_import")
@ApiModel(value = "FarmlandOmsOrderTemplateImportEntity对象", description = "订单导入模版表")
public class FarmlandOmsOrderTemplateImportEntity {

    @TableId("id")
    private Long id;
}
