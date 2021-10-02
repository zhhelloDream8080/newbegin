package com.hao.newbegin.rabbitmq.workquene;

import com.hao.newbegin.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("WorkQueneConsumer2Service")
public class Consumer2Service {
    public void consumeMessage() throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        channel.basicQos(1);//每次只能消费一个消息
        //参数2:消息自动确认 true 消费者自动向rabbitmq确认消息消费，false 不会自动确认消息
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("消费者2:"+new String(body));
                //参数1：确认队列中哪个具体消息 参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);//手动确认消息
            }
        });
    }
}
