package com.harvest.wms.repository.entity;

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
 * @since 2023-01-15 16:55:08
 */
@Getter
@Setter
@TableName("farmland_wms_inbound_bill")
@ApiModel(value = "FarmlandWmsInboundBillEntity对象", description = "")
public class FarmlandWmsInboundBillEntity {

    @TableId("id")
    private Long id;
}
