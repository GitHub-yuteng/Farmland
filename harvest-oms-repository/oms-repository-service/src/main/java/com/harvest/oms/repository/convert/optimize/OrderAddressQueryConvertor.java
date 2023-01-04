package com.harvest.oms.repository.convert.optimize;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.harvest.oms.repository.convert.OrderOptimizeQueryConvertor;
import com.harvest.oms.repository.entity.FarmlandOmsOrderAddressEntity;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderAddressMapper;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.repository.query.order.pack.OrderAddressQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/4 10:01 PM
 * @Description: 订单地址优化查询
 **/
@Component
public class OrderAddressQueryConvertor implements OrderOptimizeQueryConvertor {

    @Autowired
    private FarmlandOmsOrderAddressMapper farmlandOmsOrderAddressMapper;

    @Override
    public Collection<Long> convert(Long companyId, PageOrderConditionQuery condition, Map<String, Object> params) {
        System.out.println("optimize address.");
        OrderAddressQuery orderAddress = condition.getOrderAddress();
        if (Objects.isNull(orderAddress)) {
            return Collections.emptyList();
        }
        List<FarmlandOmsOrderAddressEntity> entities = farmlandOmsOrderAddressMapper.selectList(new QueryWrapper<FarmlandOmsOrderAddressEntity>().lambda()
                .select(FarmlandOmsOrderAddressEntity::getOrderId)
                .eq(StringUtils.isNotEmpty(orderAddress.getPostalCode()), FarmlandOmsOrderAddressEntity::getPostalCode, orderAddress.getPostalCode())
                .eq(FarmlandOmsOrderAddressEntity::getIsDeleted, false)
        );
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.singletonList(0L);
        }

        return entities.stream().map(FarmlandOmsOrderAddressEntity::getOrderId).distinct().collect(Collectors.toList());
    }

}
