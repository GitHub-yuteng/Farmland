package com.harvest.oms.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Alodi
 * @Date: 2022/12/8 9:56 PM
 */
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.harvest")
@SpringBootApplication(scanBasePackages = {"com.harvest"})
public class HarvestOmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarvestOmsServiceApplication.class, args);

    }
}
