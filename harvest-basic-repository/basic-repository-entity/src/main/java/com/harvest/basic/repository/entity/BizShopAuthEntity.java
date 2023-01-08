package com.harvest.basic.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-01-08 23:11:04
 */
@Getter
@Setter
@TableName("biz_shop_auth")
@ApiModel(value = "BizShopAuthEntity对象", description = "")
public class BizShopAuthEntity {

    @TableField("id")
    private Long id;

    @TableField("SHOP_ID")
    private Long shopId;
}
