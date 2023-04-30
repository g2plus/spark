package com.itheima.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
/**
 * @Configuration 设置为一配置,可进行DI,参数的设置
 */
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    /**
     * @LoadBalanced开启Ribbon功能,简化restTemplate的调用
     * 无需进行导依赖,eureka集成了Ribbon
     * */
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
