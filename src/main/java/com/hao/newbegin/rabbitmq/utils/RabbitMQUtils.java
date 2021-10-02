package com.hao.newbegin.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQUtils {
    //引入rabbitmq的相关配置信息

    private static String host;

    private static String port;

    private static String userName;

    private static String password;

    private static String virtualhost;

    //下面是定义的连接信息的set方法，直接用@Value的方式值取不到
    @Value("${rabbit.mq.host}")
    public void setHost(String host) {
        this.host = host;
    }

    @Value("${rabbit.mq.port}")
    public void setPort(String port) {
        this.port = port;
    }

    @Value("${rabbit.mq.username}")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Value("${rabbit.mq.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Value("${rabbit.mq.virtualhost}")
    public void setVirtualhost(String virtualhost) {
        this.virtualhost = virtualhost;
    }
    //连接资源，重量级资源，没必要每次都新建一个连接，做设置，让每次调用都是同一个
    private static ConnectionFactory connectionFactory;
    //定义提供连接对象的方法
    public static Connection getConnection() {
        try{
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(host);
            connectionFactory.setPort(Integer.valueOf(port));
            connectionFactory.setUsername(userName);
            connectionFactory.setPassword(password);
            connectionFactory.setVirtualHost(virtualhost);
            return connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;//出现异常就返回一个null
    }

    //关闭通道和关闭连接工具的方法
    public static void closeChannelAndChannel(Channel channel,Connection connection){
        try {
            if (channel!=null){
                channel.close();
            }
            if (connection!=null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
