package com.harvest.oms.service.order.handler.section;

import com.harvest.goods.client.goods.couplet.GoodsCoupletClient;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import com.harvest.goods.repository.domain.goods.simple.SkuSimplePO;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.domain.order.OrderItemDO;
import com.harvest.oms.domain.order.goods.OrderGoodsDO;
import com.harvest.oms.domain.order.goods.OrderSkuDO;
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
        OrderGoodsDO goods = new OrderGoodsDO();
        BeanUtils.copyProperties(goodsSimplePO, goods);
        orderItemDO.setGoods(goods);
        // 设置商品规格信息
        goods.setSku(skuSimples.stream()
                .filter(skuSimplePO -> skuSimplePO.getSkuId().equals(skuId))
                .map(skuSimplePO -> {
                    OrderSkuDO orderSkuDO = new OrderSkuDO();
                    BeanUtils.copyProperties(skuSimplePO, orderSkuDO);
                    return orderSkuDO;
                }).findFirst().orElseGet(() -> {
                    LOGGER.error("商品异常！");
                    return new OrderSkuDO();
                })
        );
    }
}
