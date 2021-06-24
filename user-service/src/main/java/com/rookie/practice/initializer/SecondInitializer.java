package com.rookie.practice.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: FirstInitializer.java <br/>
 * @Date: 2020/4/1 09:53
 * @Author: NIE <br/>
 * @Version: 1.0
 */
@Slf4j
@Order(2)
public class SecondInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        // 自定义属性
        Map<String, Object> map = new HashMap<>(1);
        map.put("key2", BigDecimal.valueOf(1003));
        MapPropertySource initializer = new MapPropertySource("first initializer", map);
        // 获取环境
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.getPropertySources().addLast(initializer);
        log.info("second initializer start");
    }
}
