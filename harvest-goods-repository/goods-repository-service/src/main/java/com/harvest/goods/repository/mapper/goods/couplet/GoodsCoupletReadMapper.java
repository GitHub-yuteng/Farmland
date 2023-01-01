package com.harvest.goods.repository.mapper.goods.couplet;

import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 4:58 PM
 * @Description: TODO
 **/
@Mapper
public interface GoodsCoupletReadMapper {

    Collection<GoodsSimplePO> coupletGoods(@Param("companyId") Long companyId, @Param("collection") Collection<GoodsBaseQuery> collection);

}
