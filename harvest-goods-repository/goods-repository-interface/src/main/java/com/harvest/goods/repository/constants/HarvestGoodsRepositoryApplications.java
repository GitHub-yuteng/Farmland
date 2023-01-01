package com.harvest.goods.repository.constants;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 5:39 PM
 * @Description: TODO
 **/
public interface HarvestGoodsRepositoryApplications {

    String SERVICE_NAME = "harvest-goods-repository";

    interface Path {

        String GOODS_PATH = "/goods";

        String GOODS_COUPLET = "/GoodsCoupletReadRepositoryClient";
        String GOODS_READ = "/GoodsReadRepositoryClient";
    }
}
