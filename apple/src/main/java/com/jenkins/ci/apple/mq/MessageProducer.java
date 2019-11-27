/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MessageProducer
 * Author:   jason.yangpan
 * Date:     2019/10/18 11:42
 * Description: MessageProducer
 * History:
 * <author>          <time>          <version>          <desc>
 * jason.yangpan           2019/10/18           版本号            MessageProducer
 */
package com.jenkins.ci.apple.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class MessageProducer {
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


    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUALHOST);
        //获取连接
        Connection connection=factory.newConnection();
        //获取信道,可以有多个信道
        Channel channel=connection.createChannel();
        //信道指定队列已经队列设置
        //queueDeclare（名字，是否持久化，独占的queue， 不使用时是否自动删除，其他参数）
        channel.queueDeclare(QUEUE_NAME,true,false,true,null);
        //在开始前获取一下当前时间,方便统计消息全部进入队列所需的时间
        long start=System.currentTimeMillis();
        for (int i=0;i<100000;i++){
            String message="sjw"+i;
            //basicPublish(exchange,队列名称,属性,参数.getbyte())
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        }
        //结束时间
        long end=System.currentTimeMillis();
        //输出所需时间
        System.out.println("进入队列总共耗时:"+(end-start));
        //关闭信道
        channel.close();
        //关闭连接
        connection.close();
    }
}

