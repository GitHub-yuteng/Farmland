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
 * @since 2022-12-18 21:08:51
 */
@Getter
@Setter
@TableName("farmland_after_sale_bill")
@ApiModel(value = "FarmlandAfterSaleBillEntity对象", description = "售后单表")
public class FarmlandAfterSaleBillEntity {

    @TableId("id")
    private Long id;
}
