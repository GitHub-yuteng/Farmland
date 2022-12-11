package com.harvest.core.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class HarvestConfiguration {

    @LoadBalanced
    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}