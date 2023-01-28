package com.harvest.goods.web.controller.goods.read;

import com.harvest.core.domain.Page;
import com.harvest.core.domain.ResponseResult;
import com.harvest.core.path.HarvestGoodsPath;
import com.harvest.goods.client.goods.read.GoodsReadClient;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Alodi
 * @Date: 2023/1/12 6:15 PM
 * @Description:
 **/
@Api(value = "商品读服务", tags = "商品读服务")
@RestController
@RequestMapping(value = HarvestGoodsPath.GoodsPath.GOODS_READ_PATH)
public class GoodsReadController {

    @Autowired
    private GoodsReadClient goodsReadClient;

    @ApiOperation("商品查询")
    @RequestMapping(value = "/get")
    public ResponseResult<GoodsSimplePO> getGoodsSimplePO() {
        return ResponseResult.success(null);
    }

    @ApiOperation("商品查询")
    @RequestMapping(value = "/query")
    public ResponseResult<List<GoodsSimplePO>> queryGoodsSimplePO() {
        return ResponseResult.success(null);
    }

    @ApiOperation("商品查询")
    @RequestMapping(value = "/list")
    public ResponseResult<List<GoodsSimplePO>> listByIdGoodsSimplePO() {
        return ResponseResult.success(null);
    }

    @ApiOperation("商品查询")
    @RequestMapping(value = "/page")
    public ResponseResult<Page<GoodsSimplePO>> pageGoodsSimplePO() {
        return ResponseResult.success(null);
    }

}
