package com.huang.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Productor {
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
            String queueName="queue1";
            /*
             参数1： 队列名称
             参数2： 是否要持久化，持久化就是消息是否存入硬盘,服务器重启不会丢失。
             参数3： 排他性，是否是独占
             参数4： 是否自动删除，最后一个消费者消息完毕，队列是否删除
             参数5： 附属参数
             */
            channel.queueDeclare(queueName,false,false,false,null);
            //5，准备消息内容
            String message="你好，我是黄宏涛，第一次使用rabbitmq";
            //6，发送消息给队列queen
            channel.basicPublish("",queueName,null,message.getBytes());
            System.out.println("消息发送成功");

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
