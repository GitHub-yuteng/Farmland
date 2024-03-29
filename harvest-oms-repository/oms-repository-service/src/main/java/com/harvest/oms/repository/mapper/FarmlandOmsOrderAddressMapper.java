package com.harvest.oms.repository.mapper;

import com.harvest.oms.repository.entity.FarmlandOmsOrderAddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单地址信息 ｜ 地址信息只有特定环节会变更，大部分信息流转是固定的，因此单独分拆独立表；部分平台履约场景，可能获取不到地址信息； Mapper 接口
 * </p>
 *
 * @author Alodi
 * @since 2023-02-13 15:05:21
 */
@Mapper
public interface FarmlandOmsOrderAddressMapper extends BaseMapper<FarmlandOmsOrderAddressEntity> {

}
