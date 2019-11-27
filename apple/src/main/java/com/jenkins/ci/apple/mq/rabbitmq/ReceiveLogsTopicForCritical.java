package com.jenkins.ci.apple.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveLogsTopicForCritical
{

    private static final String EXCHANGE_NAME = "rsgiste.aliyun";

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

    public static void main(String[] argv) throws Exception
    {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUALHOST);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 声明转发器
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 随机生成一个队列
        String queueName = channel.queueDeclare().getQueue();

        // 接收所有与kernel相关的消息
        channel.queueBind(queueName, EXCHANGE_NAME, "app.one.rsgiste.dev");

        System.out.println(" [*] Waiting for critical messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true)
        {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();

            System.out.println(" [x] Received routingKey = " + routingKey+ ",msg = " + message + ".");
        }
    }
}