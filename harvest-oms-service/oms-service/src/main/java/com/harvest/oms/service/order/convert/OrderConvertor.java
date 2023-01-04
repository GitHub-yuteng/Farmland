package com.harvest.oms.service.order.convert;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.vo.order.OrderInfoVO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2023/1/4 5:43 PM
 * @Description: 订单结果集对象转换器
 **/
@Component
public class OrderConvertor {

    public Collection<OrderInfoVO> convert(Collection<OrderInfoDO> collection) {
        return collection.stream().map(orderInfoDO -> {
            OrderInfoVO orderInfoVO = new OrderInfoVO();
            orderInfoVO.setCompanyId(orderInfoDO.getCompanyId());
            orderInfoVO.setOrderId(orderInfoDO.getOrderId());
            orderInfoVO.setOrderNo(orderInfoDO.getOrderNo());
            return orderInfoVO;
        }).collect(Collectors.toList());
    }
}
