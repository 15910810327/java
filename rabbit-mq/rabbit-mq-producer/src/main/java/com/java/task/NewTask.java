package com.java.task;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by lyl_b on 2017/12/28.
 */
public class NewTask {
    private static final String TASK_QUEUE_NAME="task_queue";
    public static void  main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("192.168.227.134");
        factory.setPort(5672);
        factory.setUsername("lyl");
        factory.setPassword("liu19980701");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
        //分发信息
        for (int i=0;i<10;i++){
            String message="Hello RabbitMQ"+i;
            channel.basicPublish("",TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            System.out.println("NewTask send '"+message+"'");
        }
        channel.close();
        connection.close();


    }
}