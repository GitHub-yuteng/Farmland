package com.harvest.wms.repository.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.harvest")
@SpringBootApplication(scanBasePackages = {"com.harvest"})
public class HarvestWmsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarvestWmsWebApplication.class, args);
    }
}
