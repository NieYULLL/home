package com.rookie.practice.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Description: <br/>
 * @Copyright(c): 2020, Rookie <br/>
 * @ProgramName: MyEvent.java <br/>
 * @Date: 2020/4/3 12:07
 * @Author: NIE <br/>
 * @Version: 1.0
 */
public class MyEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }
}
