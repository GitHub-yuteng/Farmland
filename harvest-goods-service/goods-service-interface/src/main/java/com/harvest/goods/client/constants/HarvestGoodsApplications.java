package com.harvest.goods.client.constants;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 4:11 PM
 * @Description: TODO
 **/
public interface HarvestGoodsApplications {

    String SERVICE_NAME = "harvest-goods-service";

    interface Path {

        String GOODS_PATH = "/goods";

        String GOODS_READ = GOODS_PATH + "/GoodsReadClient";
        String GOODS_COUPLET = GOODS_PATH + "/GoodsCoupletClient";
    }
}
