package com.harvest.wms.repository.mapper;

import com.harvest.wms.repository.entity.FarmlandWmsWarehouseAddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 仓库地址信息 ｜ 地址信息只有特定环节会变更，大部分信息流转是固定的，因此单独分拆独立表 Mapper 接口
 * </p>
 *
 * @author Alodi
 * @since 2023-01-15 16:55:09
 */
@Mapper
public interface FarmlandWmsWarehouseAddressMapper extends BaseMapper<FarmlandWmsWarehouseAddressEntity> {

}
