package com.rookie.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author: niezhiqiang
 * @date: 2020/11/16 10:59
 * @description:
 */
@EnableZuulProxy
@SpringCloudApplication
public class GatewayApplication {
    public static void main(String[] args) {
        new SpringApplication(GatewayApplication.class).run(args);
    }
}
