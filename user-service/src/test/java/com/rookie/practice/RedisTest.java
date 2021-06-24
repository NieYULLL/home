package com.rookie.practice;

import com.rookie.practice.entity.SysUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.Days;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: RedisTest.java <br/>
 * @Date: 2020/3/15 14:51
 * @Author: Rookie-nie <br/>
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedisTest {

    @Resource
    private RedisTemplate<String, SysUserInfo> redisTemplate;


    private SysUserInfo user;

    @Before
    public void before() {
        user = new SysUserInfo();
        user.setId(13);
        user.setLoginName("apt021010");
        user.setRoleId(1931328);
        user.setUserGuid("88390E66-C8E4-4B74-A56B-403FAADD9EBD");
        user.setUsername("聂志强");
    }

    @Test
    public void testConnection() {
        // redisTemplate.opsForValue().set("macBookPro", "19");
       redisTemplate.opsForValue().set("user", user);
        redisTemplate.opsForZSet().add("user_score",user,34);
        redisTemplate.opsForZSet().add("user_score",new SysUserInfo(),99);
        log.info("redis:key:{},value:{}", "user", redisTemplate.opsForValue().get("user"));
    }










}
