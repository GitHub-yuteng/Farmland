package com.harvest.goods.service;

import com.harvest.goods.domain.GoodsInfoDO;
import com.harvest.goods.domain.SkuInfoDO;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:49 PM
 * @Description: TODO
 **/
public abstract class AbstractGoodsService {

    /**
     * 转换领域对象
     *
     * @param goodsSimples
     * @return
     */
    protected Collection<GoodsInfoDO> convent(Collection<GoodsSimplePO> goodsSimples) {
        return goodsSimples.stream().map(goodsSimplePO -> {
            GoodsInfoDO goodsInfoDO = new GoodsInfoDO();
            BeanUtils.copyProperties(goodsSimplePO, goodsInfoDO);
            List<SkuInfoDO> skus = goodsInfoDO.getSkuSimples().stream().map(skuSimplePO -> {
                SkuInfoDO skuInfoDO = new SkuInfoDO();
                BeanUtils.copyProperties(skuSimplePO, skuInfoDO);
                return skuInfoDO;
            }).collect(Collectors.toList());
            goodsInfoDO.setSkus(skus);
            return goodsInfoDO;
        }).collect(Collectors.toList());
    }
}
