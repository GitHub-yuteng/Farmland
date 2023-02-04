package com.harvest.goods.repository.client.read;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.goods.repository.constants.HarvestGoodsRepositoryApplications;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 5:44 PM
 * @Description: TODO
 **/
@HarvestService(path = HarvestGoodsRepositoryApplications.Path.GOODS_READ)
public class GoodsReadRepositoryClientImpl implements GoodsReadRepositoryClient {
}
