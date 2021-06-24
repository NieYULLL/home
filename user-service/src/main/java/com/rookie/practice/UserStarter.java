package com.rookie.practice;

import com.rookie.practice.initializer.SecondInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: UserStarter.java <br/>
 * @Date: 2020/1/5 22:37
 * @Author: Rookie-nie <br/>
 * @Version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class UserStarter {
    public static void main(String[] args) {
        //硬编码方式添加系统初始化器
        System.setProperty("es.set.netty.runtime.available.processors", "false");
         SpringApplication springApplication = new SpringApplication(UserStarter.class);
         springApplication.addInitializers(new SecondInitializer());
         springApplication.run(args);

    }
}
