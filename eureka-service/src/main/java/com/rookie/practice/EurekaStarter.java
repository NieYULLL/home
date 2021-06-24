package com.rookie.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: EurekaStarter.java <br/>
 * @Date: 2020/1/5 19:22
 * @Author: Rookie-nie <br/>
 * @Version: 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaStarter {
    public static void main(String[] args) {
        SpringApplication.run(EurekaStarter.class, args);
    }
}
