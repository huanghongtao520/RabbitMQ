package com.rabbitmqconsumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = "sms.fanout.queue")
@Service
public class FanoutSMSConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("SMS:："+message);
    }
}
