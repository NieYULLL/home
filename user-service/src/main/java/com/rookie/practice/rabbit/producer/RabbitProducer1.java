package com.rookie.practice.rabbit.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: niezhiqiang
 * @date: 2021/4/15 09:57
 * @description:
 */
public class RabbitProducer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("111.231.228.138");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("niezhiqiang_1");
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("QUEUE_NAME", false, false, false, null);
            String message = "聂志强 你好";
            channel.basicPublish("", "QUEUE_NAME", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

        }
    }
}
