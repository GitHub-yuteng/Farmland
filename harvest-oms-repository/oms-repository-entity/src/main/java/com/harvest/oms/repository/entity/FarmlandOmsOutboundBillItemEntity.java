package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alodi
 * @since 2022-12-18 21:08:53
 */
@Getter
@Setter
@TableName("farmland_oms_outbound_bill_item")
@ApiModel(value = "FarmlandOmsOutboundBillItemEntity对象", description = "")
public class FarmlandOmsOutboundBillItemEntity {

    @TableId("id")
    private Long id;
}
