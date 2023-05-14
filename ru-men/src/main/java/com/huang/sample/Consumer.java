package com.huang.sample;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) {
        // 所有的中间件技术都是基于tcp/ip协议基础之上构建，
        // 只不过rabbitmq，遵循的是amop
        //1，创建连接工厂
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection=null;
        Channel channel=null;
        try {
            //2，创建连接Connection
            connection= connectionFactory.newConnection("生产者");
            //3，通过连接获取channel
            channel=connection.createChannel();
            //4，通过创建交换机，声明队列，绑定关系，路由key,发送消息，接收消息
            channel.basicConsume("queue1", true,
                    (consumerTag, message) -> System.out.println("收到的消息是："+new String(message.getBody(),"UTF-8")),
                    consumerTag -> System.out.println("接收失败"));
            System.out.println("开始接收消息");
            System.in.read();

        }catch (Exception e){

        }finally {
            //7，关闭通道
            if (channel!=null&& channel.isOpen()){
                try {
                    channel.close();
                } catch (IOException | TimeoutException e) {
                    throw new RuntimeException(e);
                }
            }
            // 8,关闭连接
            if (connection!=null&&connection.isOpen()){
                try {
                    connection.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
