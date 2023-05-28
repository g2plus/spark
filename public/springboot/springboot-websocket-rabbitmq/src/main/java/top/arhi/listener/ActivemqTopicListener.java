//package top.arhi.listener;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//import top.arhi.service.ChatService;
//
//import javax.jms.JMSException;
//import javax.jms.TextMessage;
//
//@Component
//public class ActivemqTopicListener {
//
//    @Autowired
//    private ChatService chatService;
//
//    //topic模式的消费者
//    @JmsListener(destination="${spring.activemq.topic-name}", containerFactory="topicListener")
//    public void readActiveQueue(TextMessage textMessage) throws JMSException {
//        String text = textMessage.getText();
//        Boolean sendToWebsocket = chatService.sendMsg(text);
//        //手动确认收费
//        textMessage.acknowledge();
//        if (sendToWebsocket){
//            System.out.println("消息处理成功！ 已经推送到websocket！");
//        }
//    }
//}
