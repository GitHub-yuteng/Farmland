package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单子项交运申报信息
 * </p>
 *
 * @author Alodi
 * @since 2023-02-27 22:57:39
 */
@Getter
@Setter
@TableName("farmland_oms_order_declaration_item")
@ApiModel(value = "FarmlandOmsOrderDeclarationItemEntity对象", description = "订单子项交运申报信息")
public class FarmlandOmsOrderDeclarationItemEntity {

    @TableId("order_item_id")
    private Long orderItemId;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("order_id")
    private Long orderId;

    @TableField("spu_id")
    private Long spuId;

    @TableField("sku_id")
    private Long skuId;

    @TableField("quantity")
    private BigDecimal quantity;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
