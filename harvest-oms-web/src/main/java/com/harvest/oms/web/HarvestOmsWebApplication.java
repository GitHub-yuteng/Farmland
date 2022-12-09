package com.harvest.oms.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HarvestOmsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarvestOmsWebApplication.class, args);
    }
}
