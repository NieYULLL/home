package com.rookie.practice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author: niezhiqiang
 * @date: 2021/5/25 14:07
 * @description:
 */
public interface HelloService {

    String getUser();

    @HystrixCommand(fallbackMethod = "error")
    String getAdminName();
}
