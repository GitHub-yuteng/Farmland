package com.harvest.core.path;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:16 PM
 * @Description: 商品路径定义
 **/
public interface HarvestGoodsPath {

    String GOODS_PATH           = "/harvest/goods";

    /**
     * 基础模块路径
     */
    interface GoodsPath {

        String GOODS_READ_PATH = GOODS_PATH + "/read";
        String GOODS_WRITE_PATH = GOODS_PATH + "/write";
    }
}
