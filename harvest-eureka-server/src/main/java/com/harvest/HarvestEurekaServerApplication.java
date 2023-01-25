package com.harvest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication(scanBasePackages = {"com.harvest"})
public class HarvestEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarvestEurekaServerApplication.class, args);
    }

}
