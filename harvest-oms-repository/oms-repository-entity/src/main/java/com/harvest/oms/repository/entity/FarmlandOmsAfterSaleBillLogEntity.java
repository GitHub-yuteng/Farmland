package com.harvest.oms.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 售后单日志
 * </p>
 *
 * @author Alodi
 * @since 2023-01-29 14:52:40
 */
@Getter
@Setter
@TableName("farmland_oms_after_sale_bill_log")
@ApiModel(value = "FarmlandOmsAfterSaleBillLogEntity对象", description = "售后单日志")
public class FarmlandOmsAfterSaleBillLogEntity {

    @TableId("id")
    private Long id;
}
