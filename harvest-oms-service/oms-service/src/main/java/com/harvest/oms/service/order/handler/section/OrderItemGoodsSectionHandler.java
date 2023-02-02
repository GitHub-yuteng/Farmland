package com.harvest.oms.service.order.handler.section;

import com.harvest.goods.client.goods.couplet.GoodsCoupletClient;
import com.harvest.goods.domain.GoodsInfoDO;
import com.harvest.goods.domain.SkuInfoDO;
import com.harvest.goods.repository.domain.goods.base.GoodsBusiness;
import com.harvest.goods.repository.domain.goods.base.GoodsSwitch;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import com.harvest.goods.repository.domain.goods.simple.SkuSimplePO;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.OrderItemDO;
import com.harvest.oms.service.order.handler.OrderSectionHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 6:21 PM
 * @Description: TODO
 **/
@Order(OrderSectionHandler.Order.ORDER_ITEM_GOODS)
@Component
public class OrderItemGoodsSectionHandler implements OrderSectionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemGoodsSectionHandler.class);

    @Autowired
    private GoodsCoupletClient goodsCoupletClient;

    @Override
    public void fill(Long companyId, OrderInfoDO order) {
        this.batchFill(companyId, Collections.singleton(order));
    }

    @Override
    public void batchFill(Long companyId, Collection<OrderInfoDO> orders) {
        List<GoodsBaseQuery> baseQueries = orders.stream()
                .filter(order -> CollectionUtils.isNotEmpty(order.getOrderItems()))
                .flatMap(orderInfoDO -> orderInfoDO.getOrderItems().stream())
                .map(orderItemDO -> {
                    GoodsBaseQuery baseQuery = new GoodsBaseQuery();
                    baseQuery.setSpuId(orderItemDO.getSpuId());
                    baseQuery.setSkuId(orderItemDO.getSkuId());
                    return baseQuery;
                }).distinct().collect(Collectors.toList());

        Collection<GoodsSimplePO> goodsCoupletList = goodsCoupletClient.coupletGoods(companyId, baseQueries);
        Map<Long, GoodsSimplePO> goodsIdCoupletMap = goodsCoupletList.stream().collect(Collectors.toMap(GoodsSimplePO::getSpuId, Function.identity()));
        orders.stream().filter(orderInfoDO -> CollectionUtils.isNotEmpty(orderInfoDO.getOrderItems()))
                .forEach(orderInfoDO -> orderInfoDO.getOrderItems()
                        .forEach(orderItemDO -> {
                            Long spuId = orderItemDO.getSpuId();
                            Long skuId = orderItemDO.getSkuId();
                            GoodsSimplePO goodsSimplePO = goodsIdCoupletMap.get(spuId);
                            if (Objects.isNull(goodsSimplePO)) {
                                return;
                            }
                            this.convert(companyId, orderItemDO, skuId, goodsSimplePO);
                        })
                );
    }

    private void convert(Long companyId, OrderItemDO orderItemDO, Long skuId, GoodsSimplePO goodsSimplePO) {
        Collection<SkuSimplePO> skuSimples = goodsSimplePO.getSkuSimples();
        // copy 真实使用的商品信息
        GoodsInfoDO goodsInfoDO = new GoodsInfoDO();
        orderItemDO.setGoodsInfo(goodsInfoDO);
        // 设置商品规格信息
        goodsInfoDO.setSkus(skuSimples.stream()
                .filter(skuSimplePO -> skuSimplePO.getSkuId().equals(skuId))
                .map(skuSimplePO -> {
                    SkuInfoDO sku = new SkuInfoDO();
                    BeanUtils.copyProperties(skuSimplePO, sku);
                    return sku;
                }).collect(Collectors.toList())
        );

        // 设置商品规格信息简要
        goodsInfoDO.setSkuSimples(skuSimples.stream()
                .filter(skuSimplePO -> skuSimplePO.getSkuId().equals(skuId))
                .collect(Collectors.toList())
        );

        GoodsBusiness simpleBusiness = goodsSimplePO.getGoodsBusiness();
        if (Objects.nonNull(simpleBusiness)) {
            GoodsBusiness goodsBusiness = new GoodsBusiness();
            goodsInfoDO.setGoodsBusiness(goodsBusiness);
            // 设置商品业务字段信息
            goodsBusiness.setQualityPeriod(simpleBusiness.getQualityPeriod());
            goodsBusiness.setForbidReceivePeriod(simpleBusiness.getForbidReceivePeriod());
            goodsBusiness.setForbidSalePeriod(simpleBusiness.getForbidSalePeriod());
            goodsBusiness.setAllowAcceptPeriod(simpleBusiness.getAllowAcceptPeriod());
            goodsBusiness.setLifeCycle(simpleBusiness.getLifeCycle());
        }

        GoodsSwitch simpleSwitch = goodsSimplePO.getGoodsSwitch();
        if (Objects.nonNull(simpleSwitch)) {
            GoodsSwitch goodsSwitch = new GoodsSwitch();
            goodsInfoDO.setGoodsSwitch(goodsSwitch);
            // 设置商品设置开关
            goodsSwitch.setOpenBatch(simpleSwitch.getOpenBatch());
            goodsSwitch.setOpenValidity(simpleSwitch.getOpenValidity());
        }

        goodsInfoDO.setProductNo(goodsSimplePO.getProductNo());
        goodsInfoDO.setLengthUnit(goodsSimplePO.getLengthUnit());
        goodsInfoDO.setWeightUnit(goodsSimplePO.getWeightUnit());
        goodsInfoDO.setVolumeUnit(goodsSimplePO.getVolumeUnit());
        goodsInfoDO.setSpuId(goodsSimplePO.getSpuId());
        goodsInfoDO.setSpuCode(goodsSimplePO.getSpuCode());
        goodsInfoDO.setSpuName(goodsSimplePO.getSpuName());
        goodsInfoDO.setStatus(goodsSimplePO.getStatus());
        goodsInfoDO.setGoodsType(goodsSimplePO.getGoodsType());
        goodsInfoDO.setIsPackage(goodsSimplePO.getIsPackage());
        goodsInfoDO.setCategoryId(goodsSimplePO.getCategoryId());
        goodsInfoDO.setCategory(goodsSimplePO.getCategory());
        goodsInfoDO.setBrandId(goodsSimplePO.getBrandId());
        goodsInfoDO.setBrand(goodsSimplePO.getBrand());
        goodsInfoDO.setUnitId(goodsSimplePO.getUnitId());
        goodsInfoDO.setCompanyId(companyId);
    }
}
