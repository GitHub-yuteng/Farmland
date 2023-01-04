package com.harvest.oms.repository.convert.optimize;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.harvest.oms.repository.convert.OrderOptimizeQueryConvertor;
import com.harvest.oms.repository.entity.FarmlandOmsOrderEntity;
import com.harvest.oms.repository.mapper.FarmlandOmsOrderMapper;
import com.harvest.oms.repository.query.order.PageOrderConditionQuery;
import com.harvest.oms.repository.query.order.pack.OrderNoQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/4 10:07 PM
 * @Description: 订单编号查询条件 精准匹配查询
 **/
@Component
public class OrderNoQueryConvertor implements OrderOptimizeQueryConvertor {

    @Autowired
    private FarmlandOmsOrderMapper farmlandOmsOrderMapper;

    @Override
    public Collection<Long> convert(Long companyId, PageOrderConditionQuery condition, Map<String, Object> params) {
        System.out.println("optimize order no.");
        OrderNoQuery nos = condition.getNos();
        if (Objects.isNull(nos) || CollectionUtils.isEmpty(nos.getOrderNos())) {
            return Collections.emptyList();
        }
        Collection<String> orderNos = nos.getOrderNos();
        List<FarmlandOmsOrderEntity> entities = farmlandOmsOrderMapper.selectList(new QueryWrapper<FarmlandOmsOrderEntity>().lambda()
                .select(FarmlandOmsOrderEntity::getId)
                .eq(FarmlandOmsOrderEntity::getCompanyId, companyId)
                .in(FarmlandOmsOrderEntity::getOrderNo, orderNos)
        );
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.stream().map(FarmlandOmsOrderEntity::getId).distinct().collect(Collectors.toList());
    }
}
