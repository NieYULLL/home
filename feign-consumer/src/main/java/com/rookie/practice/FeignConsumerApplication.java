package com.rookie.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: niezhiqiang
 * @date: 2021/5/25 12:48
 * @description:
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FeignConsumerApplication {
    public static void main(String[] args) {
        new SpringApplication(FeignConsumerApplication.class).run(args);
    }
}
