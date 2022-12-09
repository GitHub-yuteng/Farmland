package com.harvest.oms.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Alodi
 * @Date: 2022/12/8 9:56 PM
 */
@EnableEurekaClient
@SpringBootApplication
public class HarvestOmsRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarvestOmsRepositoryApplication.class, args);
    }
}