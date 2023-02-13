package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 售后单表
 * </p>
 *
 * @author Alodi
 * @since 2023-02-13 15:05:20
 */
@Getter
@Setter
@TableName("farmland_oms_after_sale_bill")
@ApiModel(value = "FarmlandOmsAfterSaleBillEntity对象", description = "售后单表")
public class FarmlandOmsAfterSaleBillEntity {

    @TableId("id")
    private Long id;
}
