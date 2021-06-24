package com.rookie.practice.controller;

import com.rookie.practice.client.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: niezhiqiang
 * @date: 2021/5/25 12:51
 * @description:
 */
@RestController
public class ConsumerController {

    @Resource
    private UserClient userClient;

    @GetMapping("/feign/user/{id}")
    public String getUserById(@PathVariable("id")Integer id){
        return userClient.getUser(id);
    }

    @GetMapping("/feign/adminName")
    public String getAdminName(){
        return userClient.getAdminName();
    }

}
