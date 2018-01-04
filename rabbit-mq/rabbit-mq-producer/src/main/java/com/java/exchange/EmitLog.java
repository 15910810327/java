package com.java.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by lyl_b on 2018/1/2.
 */
public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("192.168.227.134");
        factory.setPort(5672);
        factory.setUsername("lyl");
        factory.setPassword("liu19980701");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//fanout表示分发，所有的消费者得到同样的队列信息
        //分发信息
        for (int i=0;i<5;i++){
            String message="Hello World"+i;
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
            System.out.println("EmitLog Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
