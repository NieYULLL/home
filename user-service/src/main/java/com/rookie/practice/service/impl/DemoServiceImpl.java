package com.rookie.practice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: DemoSerivceImpl.java <br/>
 * @Date: 2020/4/1 10:13
 * @Author: NIE <br/>
 * @Version: 1.0
 */
@Component
@Slf4j
public class DemoServiceImpl implements ApplicationContextAware {

    private ApplicationContext ac;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext)  {
        this.ac = applicationContext;
    }

    public String test(){
        return ac.getEnvironment().getProperty("key2");
    }

}
