package top.arhi.topic.listener;


import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.arhi.topic.config.TopicRabbitConfig;
import top.arhi.topic.domain.User;

import java.io.IOException;

@Component
public class TopicListener {


	@RabbitListener(queues = TopicRabbitConfig.QUEUE_NAME)
	public void process(User user, Message message, Channel channel) throws IOException {
		long deliveryTag = message.getMessageProperties().getDeliveryTag();
		System.out.println("->"+new String(message.getBody())+"<-");
		channel.basicAck(deliveryTag,true);
		System.out.println("----------------------------------------------------------------------------");
		System.out.println(JSON.toJSONString(user));
		//throw new RuntimeException();
	}
}
