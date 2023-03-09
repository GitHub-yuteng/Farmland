package com.harvest.wms.service.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alodi
 * @since 2023-02-01 12:49:15
 */
@Getter
@Setter
@TableName("farmland_wms_inbound_bill_item")
@ApiModel(value = "FarmlandWmsInboundBillItemEntity对象", description = "")
public class FarmlandWmsInboundBillItemEntity {

    @TableId("id")
    private Long id;
}
