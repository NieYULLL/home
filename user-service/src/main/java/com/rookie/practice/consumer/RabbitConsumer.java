package com.rookie.practice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: niezhiqiang
 * @date: 2021/4/13 19:07
 * @description:
 */
@Component
@Slf4j
public class RabbitConsumer {
    @RabbitListener(queues = {"niezhiqiang_1_name1"})
    public void processMessage(String content) {

        log.info("{}",content);
    }
}
