package com.harvest.goods.client.goods;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.goods.client.constants.HarvestGoodsApplications;
import com.harvest.goods.client.goods.read.GoodsReadClient;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import com.harvest.goods.service.AbstractGoodsService;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 4:15 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestGoodsApplications.Path.GOODS_READ)
public class GoodsReadClientImpl extends AbstractGoodsService implements GoodsReadClient {

    @Override
    public GoodsSimplePO get(Long companyId, GoodsBaseQuery baseQuery) {
        return null;
    }

    @Override
    public Collection<GoodsSimplePO> list(Long companyId, Collection<GoodsBaseQuery> baseQueries) {
        return null;
    }

}
