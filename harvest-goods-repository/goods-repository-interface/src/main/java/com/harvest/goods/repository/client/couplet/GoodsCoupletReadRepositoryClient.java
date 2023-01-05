package com.harvest.goods.repository.client.couplet;

import com.harvest.core.feign.annotation.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.goods.repository.constants.HarvestGoodsRepositoryApplications;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 5:38 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestGoodsRepositoryApplications.SERVICE_NAME, path = HarvestGoodsRepositoryApplications.Path.GOODS_COUPLET)
public interface GoodsCoupletReadRepositoryClient extends GlobalMacroDefinition {

    @ApiOperation("级联查询")
    @PostMapping(value = "/coupletGoods")
    Collection<GoodsSimplePO> coupletGoods(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<GoodsBaseQuery> conditionQueries);
}
