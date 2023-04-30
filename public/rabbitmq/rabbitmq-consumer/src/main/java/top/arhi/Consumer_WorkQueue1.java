package top.arhi;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer_WorkQueue1 {

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.获取连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("114.132.210.77");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/root");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");

        //2.获取connection
        Connection connection = connectionFactory.newConnection();

        //3.获取channel
        Channel channel = connection.createChannel();

        /**
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
         *                                  Map<String, Object> arguments)
         */


        channel.queueDeclare("work_queue",true,false,false,null);


        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body){
                System.out.println("body:"+new String(body));
            }
        };

        //basicPublish将消息到默认的交换机，简单模式,routingkey与队列的名称相同
        channel.basicConsume("work_queue", true, consumer);

    }
}
