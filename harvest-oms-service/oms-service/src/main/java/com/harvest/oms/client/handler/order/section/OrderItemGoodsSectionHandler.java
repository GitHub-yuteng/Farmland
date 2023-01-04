package com.harvest.oms.client.handler.order.section;

import com.harvest.goods.client.goods.couplet.GoodsCoupletClient;
import com.harvest.goods.domain.GoodsInfoDO;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import com.harvest.oms.client.handler.order.OrderSectionHandler;
import com.harvest.oms.domain.order.OrderInfoDO;
import org.apache.commons.collections4.CollectionUtils;
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
@Order(2)
@Component
public class OrderItemGoodsSectionHandler implements OrderSectionHandler {

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

        Collection<GoodsInfoDO> goodsCoupletList = goodsCoupletClient.coupletGoods(companyId, baseQueries);
        Map<Long, GoodsInfoDO> goodsIdCoupletMap = goodsCoupletList.stream().collect(Collectors.toMap(GoodsInfoDO::getSpuId, Function.identity()));

        orders.stream().filter(orderInfoDO -> CollectionUtils.isNotEmpty(orderInfoDO.getOrderItems()))
                .forEach(orderInfoDO -> orderInfoDO.getOrderItems()
                        .forEach(orderItemDO -> {
                            Long spuId = orderItemDO.getSpuId();
                            Long skuId = orderItemDO.getSkuId();
                            GoodsInfoDO goodsCoupletDO = goodsIdCoupletMap.get(spuId);
                            if (Objects.isNull(goodsCoupletDO)) {
                                return;
                            }
                            // copy 真实使用的商品信息
                            GoodsInfoDO goodsInfoDO = new GoodsInfoDO();
                            BeanUtils.copyProperties(goodsCoupletDO, goodsInfoDO);
                            // 剔除不是该明细的其余sku
                            goodsInfoDO.getSkus().removeIf(sku -> !sku.getSkuId().equals(skuId));
                            orderItemDO.setGoodsInfo(goodsInfoDO);
                        }));
    }
}
