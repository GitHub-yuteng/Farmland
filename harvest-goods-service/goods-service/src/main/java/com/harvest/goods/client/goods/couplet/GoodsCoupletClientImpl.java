package com.harvest.goods.client.goods.couplet;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.goods.client.constants.HarvestGoodsApplications;
import com.harvest.goods.repository.client.couplet.GoodsCoupletReadRepositoryClient;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import com.harvest.goods.service.AbstractGoodsService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;

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
    public Collection<GoodsSimplePO> coupletGoods(Long companyId, Collection<GoodsBaseQuery> baseQueries) {
        if (CollectionUtils.isEmpty(baseQueries)) {
            return Collections.emptyList();
        }
        return goodsCoupletReadRepositoryClient.coupletGoods(companyId, baseQueries);
    }
}
