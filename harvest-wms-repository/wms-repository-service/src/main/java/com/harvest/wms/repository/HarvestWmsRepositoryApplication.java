package com.harvest.wms.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Author: Alodi
 * @Date: 2023/01/15 9:56 PM
 */
@EnableOpenApi
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.harvest")
@SpringBootApplication(scanBasePackages = {"com.harvest"})
public class HarvestWmsRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarvestWmsRepositoryApplication.class, args);
    }
}