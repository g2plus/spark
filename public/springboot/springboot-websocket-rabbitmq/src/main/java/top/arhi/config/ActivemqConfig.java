//package top.arhi.config;
//
//import lombok.Data;
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.RedeliveryPolicy;
//import org.apache.activemq.command.ActiveMQQueue;
//import org.apache.activemq.command.ActiveMQTopic;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.config.JmsListenerContainerFactory;
//import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
//import org.springframework.jms.core.JmsMessagingTemplate;
//
//import javax.jms.ConnectionFactory;
//import javax.jms.Queue;
//import javax.jms.Topic;
//
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "spring.activemq")
//public class ActivemqConfig {
//
//    private String brokerUrl;
//
//    private String user;
//
//    private String password;
//
//    private String topicName;
//
//    private String queueName;
//
//
//    @Bean(name = "queue")
//    public Queue queue() {
//        return new ActiveMQQueue(queueName);
//    }
//
//
//    @Bean(name = "topic")
//    public Topic topic() {
//        return new ActiveMQTopic(topicName);
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory(){
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, brokerUrl);
//        connectionFactory.setTrustAllPackages(true);
//        connectionFactory.setRedeliveryPolicy(getRedeliveryPolicy());
//        return connectionFactory;
//    }
//
//
//    //messageConsumer的配置选项Bean
//    @Bean
//    public RedeliveryPolicy getRedeliveryPolicy(){
//        return new RedeliveryPolicy();
//    }
//
//    @Bean
//    public JmsMessagingTemplate jmsMessageTemplate(){
//        return new JmsMessagingTemplate(connectionFactory());
//    }
//    // 在Queue模式中，对消息的监听需要对containerFactory进行配置
//    @Bean("queueListener")
//    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory){
//        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        //设置消费确认方式 1: 自动确认，2： 客户端手动确认，3：自动批量确认，4 事务提交并确认。
//        factory.setSessionAcknowledgeMode(2);
//        factory.setPubSubDomain(false);
//        return factory;
//    }
//
//    //在Topic模式中，对消息的监听需要对containerFactory进行配置
//    @Bean("topicListener")
//    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory){
//        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        //设置消费确认方式 1: 自动确认，2： 客户端手动确认，3：自动批量确认，4 事务提交并确认。
//        factory.setSessionAcknowledgeMode(2);
//        factory.setPubSubDomain(true);
//        return factory;
//    }
//}
