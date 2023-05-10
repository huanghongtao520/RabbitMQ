package com.rabbitmqconsumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "email.fanout.queue")
public class FanoutEmailConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("Email："+message);
    }
}
