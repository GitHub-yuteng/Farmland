package com.harvest.goods.repository.domain.goods.core;

import com.harvest.core.domain.CompanyId;
import com.harvest.core.enums.goods.GoodsStatusEnum;
import com.harvest.core.enums.goods.GoodsTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: Alodi
 * @Date: 2022/12/30 11:37 AM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsCore extends CompanyId implements Serializable {

    private static final long serialVersionUID = 6874153447161690288L;

    @ApiModelProperty("商品Id")
    private Long spuId;

    @ApiModelProperty("商品编码")
    private String spuCode;

    @ApiModelProperty("商品名称")
    private String spuName;

    @ApiModelProperty("商品状态")
    private GoodsStatusEnum status;

    @ApiModelProperty("商品类型")
    private GoodsTypeEnum goodsTypeEnum;

    @ApiModelProperty("商品分类Id")
    private Long categoryId;

    @ApiModelProperty("商品品牌Id")
    private Long brandId;

}
