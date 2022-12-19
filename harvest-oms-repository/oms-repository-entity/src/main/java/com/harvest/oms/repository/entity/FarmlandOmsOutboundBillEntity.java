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
 * @since 2022-12-19 13:54:53
 */
@Getter
@Setter
@TableName("farmland_oms_outbound_bill")
@ApiModel(value = "FarmlandOmsOutboundBillEntity对象", description = "")
public class FarmlandOmsOutboundBillEntity {

    @TableId("id")
    private Long id;
}
