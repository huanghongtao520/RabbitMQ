package com.rabbitmqproductor.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void makeOrder(String userid,String productid,int num){
        String orderId= UUID.randomUUID().toString();

        System.out.println("订单生产成功"+orderId);
        // 通过mq完成消息分发
        // 参数1： 交换机 参数2 路由key/queue队列名称 参数3：消息内容
        String exchangeName="Direct模式";
        // 路由模式，指定路由key
        String routingKey="sms";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }
}
