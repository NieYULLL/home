package com.rookie.practice.controller;

import com.alibaba.fastjson.JSON;
import com.rookie.practice.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: niezhiqiang
 * @date: 2021/5/25 09:02
 * @description:
 */
@RestController
@Slf4j
public class ConsumerController {


    @Resource
    private HelloService helloService;

    @GetMapping("/consumer/hello")
    public String getUserInfo(){
        String user = helloService.getUser();
        log.info("{}", JSON.toJSONString(user));
        return user;
    }

    @GetMapping("/consumer/adminName")
    public String getAdminName(){
        String adminName = helloService.getAdminName();
        log.info("{}", JSON.toJSONString(adminName));
        return adminName;
    }

}
