package com.jenkins.ci.apple.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class MessageConsumer {
    //RabbitMQ服务所在地址
    public final static String HOST="192.168.43.37";
    //RabbitMQ端口
    public final static int PORT=5672;
    //RabbitMQ登陆用户名
    public final static String USERNAME="fendarabbitmq";
    //RabbitMQ登陆密码
    public final static String PASSWORD="fendarabbitmqr03";
    //队列名称
    public final static String QUEUE_NAME="topice";

    public final static String VIRTUALHOST= "one/app";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUALHOST);
        //获取连接
        Connection connection = factory.newConnection();
        //获取信道,可以有多个信道
        Channel channel = connection.createChannel();
        //信道设置,必须与要对应接收的队列设置一模一样,有差别则无法接收你想要的信道
        channel.queueDeclare(QUEUE_NAME, true, false, true, null);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //信道交给consumer进行内容接收处理
        channel.basicConsume(QUEUE_NAME,consumer);
        while (true) {  //消费者程序运行开着 如果生产者新增了数据会自动获取
            // nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[消息]" + message);
        }

    }
}
