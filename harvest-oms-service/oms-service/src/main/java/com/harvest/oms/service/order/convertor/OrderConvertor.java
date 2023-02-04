package com.harvest.oms.service.order.convertor;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.vo.order.OrderInfoVO;
import org.springframework.beans.BeanUtils;
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


    public Collection<OrderInfoVO> convertList(Collection<OrderInfoDO> collection) {
        return collection.stream().map(this::convert).collect(Collectors.toList());
    }

    public OrderInfoVO convert(OrderInfoDO order) {
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        BeanUtils.copyProperties(order, orderInfoVO);
        return orderInfoVO;
    }

}
