package com.rookie.practice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rookie.practice.service.HelloService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: niezhiqiang
 * @date: 2021/5/25 14:07
 * @description:
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @HystrixCommand(fallbackMethod = "error")
    @Override
    public String getUser() {
        return restTemplate.getForEntity("http://user-service/web/user/{1}", String.class, 1).getBody();
    }

    @HystrixCommand(fallbackMethod = "error")
    @Override
    public String getAdminName() {

        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        for (ServiceInstance instance : instances) {
            System.err.println("service:"+instance.getHost()+":"+instance.getPort());
            System.err.println("serviceId:"+instance.getServiceId());
        }

        return restTemplate.getForEntity("http://user-service/web/adminName", String.class).getBody();
    }

    public String error(){
        return "error";
    }
}
