package com.harvest.goods.repository;

import com.harvest.core.utils.JsonUtils;
import com.harvest.goods.repository.client.couplet.GoodsCoupletReadRepositoryClient;
import com.harvest.goods.repository.domain.goods.simple.GoodsSimplePO;
import com.harvest.goods.repository.query.GoodsBaseQuery;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

/**
 * @Author: Alodi
 * @Date: 2022/12/31 6:13 PM
 * @Description: TODO
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class CoupletGoodsTest {

    @Autowired
    private GoodsCoupletReadRepositoryClient goodsCoupletReadRepositoryClient;

    @Test
    public void contextLoads() {
        Long companyId = 1L;
        Collection<GoodsBaseQuery> baseQueries = Lists.newArrayList();
        Collection<GoodsSimplePO> goodsSimplePOS = goodsCoupletReadRepositoryClient.coupletGoods(companyId, baseQueries);
        System.out.println(JsonUtils.object2Json(goodsSimplePOS));
    }
}
