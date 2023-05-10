package com.rabbitmqconsumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "duanxin.fanout.queue")
public class FanoutDuanxinConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("短信："+message);
    }
}
