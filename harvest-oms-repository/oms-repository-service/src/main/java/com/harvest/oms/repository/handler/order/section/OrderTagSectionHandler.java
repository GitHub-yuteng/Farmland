package com.harvest.oms.repository.handler.order.section;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.harvest.core.enums.oms.OrderAssistEnums;
import com.harvest.core.utils.QueryUtils;
import com.harvest.oms.repository.domain.order.base.OrderTag;
import com.harvest.oms.repository.domain.order.simple.OrderSimplePO;
import com.harvest.oms.repository.entity.FarmlandOmsOrderTagEntity;
import com.harvest.oms.repository.handler.order.OrderSectionRepositoryHandler;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderTagMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 2:57 PM
 * @Description: 订单部分信息处理器（订单标记）
 **/
@Component
public class OrderTagSectionHandler implements OrderSectionRepositoryHandler<OrderSimplePO> {

    private final static Integer TAG_PARTITION_SIZE = 200;

    @Autowired
    private FarmlandOmsOrderTagMapper farmlandOmsOrderTagMapper;

    @Override
    public void save(long companyId, OrderSimplePO order) {

    }

    @Override
    public void fill(long companyId, OrderSimplePO order) {
        this.batchFill(companyId, Lists.newArrayList(order));
    }

    @Override
    public void batchFill(long companyId, Collection<OrderSimplePO> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }


        // extension 大字段 影响IO 判断存在对应的 tagValue 单独取对应的 扩展信息进行处理
        List<Long> orderIds = orders.stream().map(OrderSimplePO::getOrderId).distinct().collect(Collectors.toList());
        List<FarmlandOmsOrderTagEntity> orderTagsDB = this.partitionBatch(companyId, orderIds);
        Map<Long, List<FarmlandOmsOrderTagEntity>> orderIdTagMap = orderTagsDB.stream().collect(Collectors.groupingBy(FarmlandOmsOrderTagEntity::getRecordId));
        orders.forEach(orderSimplePO -> {
            List<FarmlandOmsOrderTagEntity> orderTags = orderIdTagMap.get(orderSimplePO.getOrderId());
            if (CollectionUtils.isEmpty(orderTags)) {
                return;
            }
            orderSimplePO.setOrderTags(orderTags.stream()
                    .map(tag -> {
                        OrderTag orderTag = new OrderTag();
                        orderTag.setTagId(tag.getId());
                        orderTag.setOrderId(tag.getRecordId());
                        orderTag.setTagValue(tag.getTagValue());
                        orderTag.setProcessed(tag.getProcessed());
                        orderTag.setSimpleExtension(tag.getSimpleExtension());
                        return orderTag;
                    }).collect(Collectors.toList())
            );
        });
    }

    /**
     * 如果订单Ids过多 分区分批查询优化
     *
     * @param companyId
     * @param orderIds
     * @return
     */
    private List<FarmlandOmsOrderTagEntity> partitionBatch(Long companyId, List<Long> orderIds) {
        return QueryUtils.partitionExecute(orderIds, TAG_PARTITION_SIZE, f -> farmlandOmsOrderTagMapper.selectList(
                new QueryWrapper<FarmlandOmsOrderTagEntity>().lambda()
                        .select(FarmlandOmsOrderTagEntity::getId,
                                FarmlandOmsOrderTagEntity::getRecordId,
                                FarmlandOmsOrderTagEntity::getRecordType,
                                FarmlandOmsOrderTagEntity::getTagValue,
                                FarmlandOmsOrderTagEntity::getProcessed,
                                FarmlandOmsOrderTagEntity::getSimpleExtension
                        )
                        .eq(FarmlandOmsOrderTagEntity::getRecordType, OrderAssistEnums.OrderTagRelationEnum.ORDER)
                        .in(FarmlandOmsOrderTagEntity::getRecordId, orderIds)));
    }

    @Override
    public void update(long companyId, long orderId, OrderSimplePO entity) {

    }
}
