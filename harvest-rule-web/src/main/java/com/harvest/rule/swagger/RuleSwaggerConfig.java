package com.harvest.rule.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:23 PM
 * Springfox 设置 Spring MVC 的路径匹配策略是 ant-path-matcher，而 Spring Boot 2.6/7.x 版本的默认匹配策略是 path-pattern-matcher
 */
@Configuration
public class RuleSwaggerConfig {

    @Value("${springfox.documentation.enabled}")
    private Boolean enable;

    @Bean
    public Docket ruleSwagger() {
        // springfox 的基本API配置
        return new Docket(DocumentationType.OAS_30)
                .pathMapping("/")
                .apiInfo(this.api())
                .groupName("Rule")
                .protocols(new HashSet<>(Arrays.asList("http", "https")))
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.harvest"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo api() {
        return new ApiInfoBuilder()
                .title("WEB API DOC")
                .description("Farmland rule-web api doc.")
                .contact(new Contact("Alodi", "https://github.com/GitHub-yuteng/Farmland", "soft_yt@163.com"))
                .version("1.0")
                .build();
    }
}
