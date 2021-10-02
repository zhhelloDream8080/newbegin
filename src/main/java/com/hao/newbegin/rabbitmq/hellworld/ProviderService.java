package com.hao.newbegin.rabbitmq.hellworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
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
@Service("ProviderService")
public class ProviderService {

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

    public ProviderService() {
    }

    public void testSendMessage(String provide) throws IOException, TimeoutException {
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
        channel.queueDeclare("hello", false, false, false, null);
        //发布消息：【一个队列可以向不同的队列发送消息】
        //参数1：交换机名称
        // 参数2：队列名称
        // 参数3：传递消息的额外设置：
        //       可以在这里设置是否对消息进行持久化，设置null代表不进行持久化（仅设置队列持久化，服务重启后消息也会丢失）
        //       MessageProperties.PERSISTENT_TEXT_PLAIN代表对消息进行持久化
        //       消费者消费的时候也要进行同样的设置，比如消费的也是进行了持久化的消息（生产者和消费者的特性要严格一致）
        // 参数4：消息的具体内容
        channel.basicPublish("","hello", null,provide.getBytes());
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
