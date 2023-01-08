package com.harvest.basic.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 店铺表
 * </p>
 *
 * @author Alodi
 * @since 2023-01-08 23:11:04
 */
@Getter
@Setter
@TableName("biz_shop")
@ApiModel(value = "BizShopEntity对象", description = "店铺表")
public class BizShopEntity {

    @TableId("id")
    private Long id;

    @TableField("COMPANY_ID")
    private Long companyId;
}
