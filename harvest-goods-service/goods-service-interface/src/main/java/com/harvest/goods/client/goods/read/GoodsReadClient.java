package com.harvest.goods.client.goods.read;

import com.harvest.core.annotation.feign.HarvestClient;
import com.harvest.core.constants.GlobalMacroDefinition;
import com.harvest.goods.client.constants.HarvestGoodsApplications;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
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
@HarvestClient(name = HarvestGoodsApplications.SERVICE_NAME, path = HarvestGoodsApplications.Path.GOODS_READ)
public interface GoodsReadClient extends GlobalMacroDefinition {

    @PostMapping(value = "/get")
    GoodsSimplePO get(@RequestParam(COMPANY_ID) Long companyId, @RequestBody GoodsBaseQuery baseQuery);

    @PostMapping(value = "/list")
    Collection<GoodsSimplePO> list(@RequestParam(COMPANY_ID) Long companyId, @RequestBody Collection<GoodsBaseQuery> baseQueries);
}
