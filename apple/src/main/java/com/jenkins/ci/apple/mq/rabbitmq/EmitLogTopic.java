package com.jenkins.ci.apple.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.UUID;

public class EmitLogTopic {

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

    public static void main(String[] argv) throws Exception {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUALHOST);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        for (int i=0;i<1000000;i++){
            String message="jerhufduyshrfjuhhj你是最棒的充滿悲傷八點半上班的你不回家三個代表你還不是的説的每句話官方報價和BASF麻花辮愛説大話艱難跋涉v的繳納水電費的薩芬和環境那首歌v就那啥v叫啥啊環境的撒謊價格v和技術大會上大哈根VS的驕傲v劇的數據啊大蘇打海軍部隊4654645645645645654654444444444444444444444444444444444" +
                    "app.one.rsgiste.dev"+i;
            String msg = UUID.randomUUID().toString();
            channel.basicPublish(EXCHANGE_NAME, "app.one.rsgiste.dev", null, message.getBytes());
            System.out.println(" [x] Sent routingKey = " + "app.one.rsgiste.dev" + " ,msg = " + msg + ".");
        }

        channel.close();
        connection.close();
    }
}