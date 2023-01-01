package com.harvest.goods.client.goods.couplet;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.goods.client.constants.HarvestGoodsApplications;
import com.harvest.goods.domain.GoodsInfoDO;
import com.harvest.goods.repository.client.couplet.GoodsCoupletReadRepositoryClient;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import com.harvest.goods.service.AbstractGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 4:15 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestGoodsApplications.Path.GOODS_COUPLET)
public class GoodsCoupletClientImpl extends AbstractGoodsService implements GoodsCoupletClient {

    @Autowired
    private GoodsCoupletReadRepositoryClient goodsCoupletReadRepositoryClient;

    @Override
    public Collection<GoodsInfoDO> coupletGoods(Long companyId, Collection<GoodsBaseQuery> baseQueries) {
        Collection<GoodsSimplePO> goodsSimples = goodsCoupletReadRepositoryClient.coupletGoods(companyId, baseQueries);
        return super.convent(goodsSimples);
    }
}
