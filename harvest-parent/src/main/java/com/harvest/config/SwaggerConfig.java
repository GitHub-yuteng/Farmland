package com.harvest.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 8:23 PM
 * Springfox 设置 Spring MVC 的路径匹配策略是 ant-path-matcher，而 Spring Boot 2.6/7.x 版本的默认匹配策略是 path-pattern-matcher
 * springfoxHandlerProviderBeanPostProcessor 解决报错
 */
@Component
public class SwaggerConfig {

    @Bean
    public Docket api() {
        // springfox 的基本API配置
        return new Docket(DocumentationType.OAS_30)
                // 将映射的路径当作base Path，添加给apis
                .pathMapping("/")
                .apiInfo(new ApiInfoBuilder()
                        .contact(new Contact("Alodi", "https://github.com/GitHub-yuteng/Farmland", "soft_yt@163.com"))
                        .description("Farmland api doc")
                        .title("WEB API DOC")
                        .version("1.0")
                        .build())
                .groupName("测试环境")
                // 支持的协议
                .protocols(new HashSet<>(Arrays.asList("http", "https")))
                .enable(true)
                // 创建API 选择器
                .select()
                // 暴露的给Swagger的API，指定扫描包的接口
                .apis(RequestHandlerSelectors.basePackage("com.harvest.oms.web"))
                // 符合要求的接口
//                .paths(PathSelectors.ant("/user/**"))
                .build();
    }

    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream().filter(mapping -> mapping.getPatternParser() == null).collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    assert field != null;
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}
