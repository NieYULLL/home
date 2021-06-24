package com.rookie.practice.web.controller;

import com.alibaba.fastjson.JSON;
import com.rookie.practice.entity.SysUserInfo;
import com.rookie.practice.service.IUserService;
import com.rookie.practice.service.impl.DemoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: UserController.java <br/>
 * @Date: 2020/1/6 09:59
 * @Author: Rookie-nie <br/>
 * @Version: 1.0
 */
@RestController
@RequestMapping("/web")
@Slf4j
@Api("用户web模块")

@RefreshScope
public class UserController {
    private final IUserService userService;
    private final DemoServiceImpl demoService;

    // @Value("${admin.username}")
    // private String adminName;

    @Autowired
    public UserController(IUserService userService, DemoServiceImpl demoService) {
        this.userService = userService;
        this.demoService = demoService;
    }

    @GetMapping(value = "/user/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "通过用户ID查询用户信息", notes = "使用restFUL风格")
    public String getUserById(@PathVariable Integer id) {
        return userService.getUserById(id).toString();
    }

    @PutMapping("/user/{id}")
    @ApiOperation(value = "通过用户ID更新用户信息", notes = "使用restFUL风格")
    public Boolean updateUser(@RequestBody SysUserInfo userInfo, @PathVariable Integer id) {
        log.info("user更新入参：{}，id：{}", JSON.toJSONString(userInfo), id);
        return userService.updateUser(userInfo, id);
    }
    @PostMapping("/user")
    @ApiOperation(value = "通过用户ID更新用户信息", notes = "使用restFUL风格")
    public Boolean insertUser(@RequestBody SysUserInfo userInfo) throws IOException {
        return userService.insertUser(userInfo);
    }

    @GetMapping("/users")
    @ApiOperation(value = "通过用户ID更新用户信息", notes = "使用restFUL风格")
    public List<SysUserInfo> getAllUser() {
        return userService.getAllUser();
    }


    @GetMapping("/test")
    public String test() {
        return demoService.test();
    }

    @GetMapping("/adminName")
    public String getAdminName() {
        return "adminName";
    }
}
