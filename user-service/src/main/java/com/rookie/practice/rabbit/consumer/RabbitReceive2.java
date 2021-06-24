package com.rookie.practice.rabbit.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: niezhiqiang
 * @date: 2021/4/15 10:04
 * @description: Publish/Subscribe
 */
public class RabbitReceive2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("111.231.228.138");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("niezhiqiang_1");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, "logs", "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });
    }
}
