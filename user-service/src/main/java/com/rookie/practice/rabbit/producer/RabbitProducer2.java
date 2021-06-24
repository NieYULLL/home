package com.rookie.practice.rabbit.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author: niezhiqiang
 * @date: 2021/4/15 09:57
 * @description: Publish/Subscribe
 */
public class RabbitProducer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("111.231.228.138");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("niezhiqiang_1");
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
           channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);
           channel.basicPublish("logs","",null,"聂志强，你好".getBytes(StandardCharsets.UTF_8));
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue,"logs","");


        }
    }
}
