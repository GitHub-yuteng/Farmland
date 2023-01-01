package com.harvest.goods.client.goods.couplet;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.goods.client.constants.HarvestGoodsApplications;
import com.harvest.goods.domain.GoodsInfoDO;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 4:11 PM
 * @Description: TODO
 **/
@HarvestClient(name = HarvestGoodsApplications.SERVICE_NAME, path = HarvestGoodsApplications.Path.GOODS_COUPLET)
public interface GoodsCoupletClient extends GlobalMacroDefinition {

    @PostMapping(value = "/coupletGoods")
    Collection<GoodsInfoDO> coupletGoods(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<GoodsBaseQuery> baseQueries);

}
