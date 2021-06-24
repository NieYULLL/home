package com.rookie.practice.utils;

import com.rookie.practice.entity.SysUserInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: RedisUtils.java <br/>
 * @Date: 2020/3/15 16:12
 * @Author: Rookie-nie <br/>
 * @Version: 1.0
 */
@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String, SysUserInfo> redisTemplate;

}
