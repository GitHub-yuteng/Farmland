package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
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
 * @since 2023-02-27 22:57:38
 */
@Getter
@Setter
@TableName("farmland_oms_order_declaration")
@ApiModel(value = "FarmlandOmsOrderDeclarationEntity对象", description = "订单交运申报信息")
public class FarmlandOmsOrderDeclarationEntity {

    @TableId("order_id")
    private Long orderId;

    @TableField("COMPANY_ID")
    private Long companyId;

    @TableField("status")
    private Integer status;

    @TableField("last_response")
    private String lastResponse;

    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
