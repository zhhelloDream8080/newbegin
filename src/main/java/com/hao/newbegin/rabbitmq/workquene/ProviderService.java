package com.hao.newbegin.rabbitmq.workquene;

import com.hao.newbegin.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service("WorkQueneProvideService")
public class ProviderService {
   public void sendMessage() throws IOException {
       //获取连接对象
       Connection connection = RabbitMQUtils.getConnection();
       //获取通道对象
       Channel channel = connection.createChannel();
       //通过通道声明队列
       channel.queueDeclare("work", true, false, false, null);
       //生产消息，做测试用，多发几个消息
       for (int i=0;i<10;i++){
           channel.basicPublish("","work",null,(i+"===>hello work queue").getBytes());
       }
       //关闭资源
       RabbitMQUtils.closeChannelAndChannel(channel,connection);
   }
}
