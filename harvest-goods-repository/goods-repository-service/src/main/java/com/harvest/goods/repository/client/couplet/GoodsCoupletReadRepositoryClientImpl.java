package com.harvest.goods.repository.client.couplet;

import com.harvest.core.feign.annotation.HarvestService;
import com.harvest.goods.repository.constants.HarvestGoodsRepositoryApplications;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import com.harvest.goods.repository.mapper.goods.couplet.GoodsCoupletReadMapper;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 5:44 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestGoodsRepositoryApplications.Path.GOODS_COUPLET)
public class GoodsCoupletReadRepositoryClientImpl implements GoodsCoupletReadRepositoryClient {

    @Autowired
    private GoodsCoupletReadMapper goodsCoupletReadMapper;

    @Override
    public Collection<GoodsSimplePO> coupletGoods(Long companyId, Collection<GoodsBaseQuery> baseQueries) {
        return goodsCoupletReadMapper.coupletGoods(companyId, baseQueries);
    }
}
