package com.harvest.goods.repository.client.read;

import com.harvest.core.feign.annotation.HarvestClient;
import com.harvest.goods.repository.constants.HarvestGoodsRepositoryApplications;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 5:38 PM
 * @Description: TODO
 **/
@HarvestClient(value = HarvestGoodsRepositoryApplications.SERVICE_NAME, path = HarvestGoodsRepositoryApplications.Path.GOODS_COUPLET)
public interface GoodsReadRepositoryClient {

}
