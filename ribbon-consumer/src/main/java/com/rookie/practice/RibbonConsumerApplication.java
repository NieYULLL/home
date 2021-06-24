package com.rookie.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: niezhiqiang
 * @date: 2021/5/25 08:53
 * @description:
 */
@SpringCloudApplication
@EnableDiscoveryClient
public class RibbonConsumerApplication {
    public static void main(String[] args) {
        new SpringApplication(RibbonConsumerApplication.class).run(args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}



