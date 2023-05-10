package com.rabbitmqconsumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "sms.fanout.queue")
public class FanoutSMSConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("SMS:："+message);
    }
}
