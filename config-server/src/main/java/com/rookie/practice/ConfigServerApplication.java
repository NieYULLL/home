package com.rookie.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author: niezhiqiang
 * @date: 2021/6/9 21:16
 * @description:
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        new SpringApplication(ConfigServerApplication.class).run(args);

    }
}
