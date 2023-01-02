package com.harvest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Alodi
 * @Date: 2023/1/1 8:59 PM
 * @Description: TODO
 **/
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.harvest")
@SpringBootApplication(scanBasePackages = "com.harvest")
public class HarvestWebStarter {

    public static void main(String[] args) {
        SpringApplication.run(HarvestWebStarter.class, args);
    }
}
