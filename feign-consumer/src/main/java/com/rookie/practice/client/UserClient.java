package com.rookie.practice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: niezhiqiang
 * @date: 2021/5/25 12:52
 * @description:
 */
@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/web/user/{id}")
    String getUser(@PathVariable("id") Integer id);

    @GetMapping("/web/adminName")
    String getAdminName();
}
