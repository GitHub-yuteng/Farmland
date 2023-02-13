package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单交运申报信息
 * </p>
 *
 * @author Alodi
 * @since 2023-02-13 15:05:21
 */
@Getter
@Setter
@TableName("farmland_oms_order_declaration")
@ApiModel(value = "FarmlandOmsOrderDeclarationEntity对象", description = "订单交运申报信息")
public class FarmlandOmsOrderDeclarationEntity {

    @TableId("order_id")
    private Long orderId;
}
