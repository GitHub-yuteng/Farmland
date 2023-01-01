package com.harvest.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Author: Alodi
 * @Date: 2022/12/8 9:56 PM
 */
@EnableOpenApi
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.harvest")
@SpringBootApplication(scanBasePackages = {"com.harvest"})
public class HarvestGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarvestGoodsServiceApplication.class, args);
    }
}
