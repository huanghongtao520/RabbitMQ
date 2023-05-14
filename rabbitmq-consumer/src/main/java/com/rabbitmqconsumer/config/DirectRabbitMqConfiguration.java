package com.rabbitmqconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitMqConfiguration {
    //1.声明direct模式交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("Direct模式",true,false);
    }

    //2.声明队列
    @Bean
    public Queue smsQueue(){
        return new Queue("sms.fanout.queue",true);
    }
    @Bean
    public Queue duanxinQueue(){
        return new Queue("duanxin.fanout.queue",true);
    }
    @Bean
    public Queue emailQueue(){
        return new Queue("email.fanout.queue",true);
    }

    // 3. 完成队列和交换机绑定关系
    @Bean
    public Binding duanxinBindding(){
        return BindingBuilder.bind(duanxinQueue()).to(directExchange()).with("duanxin");
    }
    @Bean
    public Binding smsBindding(){
        return BindingBuilder.bind(smsQueue()).to(directExchange()).with("sms");
    }
    @Bean
    public Binding emailBindding(){
        return BindingBuilder.bind(emailQueue()).to(directExchange()).with("email");
    }
}
