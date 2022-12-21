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
 * 订单备注表
 * </p>
 *
 * @author Alodi
 * @since 2022-12-21 18:15:57
 */
@Getter
@Setter
@TableName("farmland_oms_order_remark")
@ApiModel(value = "FarmlandOmsOrderRemarkEntity对象", description = "订单备注表")
public class FarmlandOmsOrderRemarkEntity {

    @ApiModelProperty("primary key")
    @TableId("order_id")
    private Long orderId;

    @ApiModelProperty("公司ID")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty("卖家备注")
    @TableField("seller_remark")
    private String sellerRemark;

    @ApiModelProperty("买家备注")
    @TableField("buyer_remark")
    private String buyerRemark;

    @ApiModelProperty("系统备注")
    @TableField("system_remark")
    private String systemRemark;

    @ApiModelProperty("需要打印备注")
    @TableField("print_remark")
    private String printRemark;

    @ApiModelProperty("记录创建时间")
    @TableField(value = "rc_time", fill = FieldFill.INSERT)
    private LocalDateTime rcTime;

    @ApiModelProperty("记录修改时间")
    @TableField(value = "rm_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime rmTime;
}
