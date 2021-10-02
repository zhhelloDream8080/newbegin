package com.hao.newbegin.rabbitmq.hellworld;

import com.hao.newbegin.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 新建一个mq中的生产者类，产生消息
 * 有个小坑，此处记录一下：
 * 1，配置文件里配置的信息比如“rabbit.mq.host”如果是在单元测试下用@value导入是不起作用的，
 *    必须是项目启动以后才行，因为需要加载那些配置信息才可以
 * 2,新建的controller必须在com.hao.newbegin包下才能生效
 */
@Service("ConsumerService")
public class ConsumerService {

    //引入rabbitmq的相关配置信息
    @Value("${rabbit.mq.host}")
    private String host;
    @Value("${rabbit.mq.port}")
    private String port;
    @Value("${rabbit.mq.username}")
    private String userName;
    @Value("${rabbit.mq.password}")
    private String password;
    @Value("${rabbit.mq.virtualhost}")
    private String virtualhost;

    public ConsumerService() {
    }

    public void consumeMessage() throws IOException, TimeoutException {
        //创建连接mq的连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq的主机
        connectionFactory.setHost(host);
        //设置端口号
        connectionFactory.setPort(Integer.valueOf(port));
        //设置用户名和密码
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        //设置虚拟主机
        connectionFactory.setVirtualHost(virtualhost);

        //获取连接对象
        Connection connection = connectionFactory.newConnection();

        //通过工具类来创建连接
//        Connection connection1 = RabbitMQUtils.getConnection();

        //获取连接中的通道
        Channel channel = connection.createChannel();
        //通道绑定对应的消息队列
        //参数1：队列名称，如果队列不存在则自动创建
        //参数2：用来定义队列特性是否需要持久化：true 持久化队列（将数据存储到磁盘中，下次重启队列仍旧存在）
        //                                 false 不持久化队列
        //参数3：exclusive是否独占队列（该队列只能被当前声明的连接使用，不能被其它连接使用）
        //                          true：独占队列  false：不独占队列
        //参数4：autoDelete：是否在消费完成后自动删除队列，true：删除队列，false：不删除队列
        //参数5：额外附加参数
        //生产者和消费者要在同一个队列里面，同时两者的参数设置要保持一致
        channel.queueDeclare("hello", false, false, false, null);
        //消费消息
        //参数1：消费那个队列的消息  队列名称（注意此处没有交换机了）
        //参数2：开始消息的自动确认机制
        //参数3：消费时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("开始消费消息:"+new String(body));
            }
        });
        //关闭通道和连接，在消费者模式里它需要一直去监听，所以不建议关闭该连接
//        channel.close();
//        connection.close();

        //通过工具类来关闭连接
//        RabbitMQUtils.closeChannelAndChannel(channel,connection);
    }
}
