package com.rookie.practice.springlistener;

import com.rookie.practice.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: MyApplicationListener.java <br/>
 * @Date: 2020/4/3 12:02
 * @Author: NIE <br/>
 * @Version: 1.0
 */
@Slf4j
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MyEvent) {
            log.info("自定义监听器捕捉到事件，当前时间：{}", new Date(event.getTimestamp()));
        }
    }
}
