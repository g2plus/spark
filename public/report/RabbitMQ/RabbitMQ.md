RabbitMQ:

消息中间件

工作：异步削峰解耦

简单模式：

![](D:\develop\report\image\简单模式.png)

p:producer生产方——信息发送方

消息发送方需要设置的信息

ConnectionFactory：连接工厂，发起连接

Connection: 用于创建信道进行通信

Channel：通信频道

设置消息队列的名称

设置RabbitMQ Server的ip地址



消息发送方无法发送消息的可能原因：

Maybe the broker was started without enough free disk space (by default it needs at least 200 MB free) and is therefore refusing to accept messages

默认情况下，RabbitMQ消息需要200Mb的空间大小。解决方案：修改配置文件

https://www.rabbitmq.com/configure.html#config-items



c：consumer消费方——消息接收方

ConnectionFactory：连接工厂，发起连接

Connection：用于创建信道进行通信

设置RabbitMQ Server的ip地址

Channel：创建通信通道

设置队列与发送方保持一致

额外设置DeliverCallback：用来缓冲需要被消费的信息。

```java
channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
```



为什么需要确保信息的安全？

 Since protocol methods (messages) sent are not guaranteed to reach the peer or be successfully processed by it, both publishers and consumers need a mechanism for delivery and processing confirmation.



RabbitMQ采用确保信息安全的机制及底层根据

Delivery processing acknowledgements from [consumers](https://www.rabbitmq.com/consumers.html) to RabbitMQ are known as acknowledgements in messaging protocols; broker acknowledgements to [publishers](https://www.rabbitmq.com/publishers.html) are a protocol extension called [publisher confirms](https://www.rabbitmq.com/confirms.html#publisher-confirms). Both features build on the same idea and are inspired by TCP.



消费方：consumer acknowledgement

<font color=red>RabbitMQ收到消息回执（consumer acknowledgment）后才将该消息从Queue中移除；如果RabbitMQ没有收到回执并检测到消费者的RabbitMQ连接断开，则RabbitMQ会将该消息发送给其他消费者（如果存在多个消费者）进行处理。这里不存在timeout概念，一个消费者处理消息时间再长也不会导致该消息被发送给其他消费者，除非它的RabbitMQ连接断开。</font>



消费方确认接受到消息的模式：

1.消息发出(写入到TCP socket)，确认接受到消息

2.explicit ("manual") client acknowledgement（手工确认模式）



手工确认模式（消费方接受到消息后应答模式）（监听channel）

basic.ack is used for positive acknowledgements

basic.nack is used for negative acknowledgements (note: this is a [RabbitMQ extension to AMQP 0-9-1](https://www.rabbitmq.com/nack.html))

basic.reject is used for negative acknowledgements but has one limitation compared to basic.nack

优点：避免信息丢失，可结合bounded channel来避免消费方内存耗尽的问题。

但是在服务器down机情况下，消息没有被执行仍然会导致信息丢失。



自动确认模式

消息发出时(写入到TCP Socket)，消费方成功接受到消息

存在的问题：Unlike with manual acknowledgement model, if consumers's TCP connection or channel is closed before successful delivery, the message sent by the server will be lost.

如果消费方的TCP连接或者channel在信息发送到之前关闭，会出现信息丢失。

consumer overload，消费方负载过高，导致堆内存耗尽。



在开发当中，应该采用手工确认模式

手工确认一般采用的是basic.ack  java client users will use Channel#basicAck o perform a basic.ack 

```java
boolean autoAck = false;//关闭自动确认机制
channel.basicConsume(queueName, autoAck, "a-consumer-tag",
     new DefaultConsumer(channel) {
         @Override
         public void handleDelivery(String consumerTag,
                                    Envelope envelope,
                                    AMQP.BasicProperties properties,
                                    byte[] body)
             throws IOException
         {
             long deliveryTag = envelope.getDeliveryTag();
             // positively acknowledge a single delivery, the message will
             // be discarded
             channel.basicAck(deliveryTag, false);//开启手工确认机制，如果需要开启批量确认机制，应该设置channel的basicAck的第二个参数为true
         }
     });
```

官方解析：Manual acknowledgements can be batched to reduce network traffic. This is done by setting the multiple field of acknowledgement methods (see above) to true. Note that basic.reject doesn't historically have the field and that's why basic.nack was introduced by RabbitMQ as a protocol extension.

When the multiple field is set to true, RabbitMQ will acknowledge all outstanding delivery tags up to and including the tag specified in the acknowledgement. Like everything else related to acknowledgements, this is scoped per channel. For example, given that there are delivery tags 5, 6, 7, and 8 unacknowledged on channel Ch, when an acknowledgement frame arrives on that channel with delivery_tag set to 8 and multiple set to true, all tags from 5 to 8 will be acknowledged. If multiple was set to false, deliveries 5, 6, and 7 would still be unacknowledged.

批确认可以节流，缺点：如果acknowledgement frame 指向是的channel存在没有确认的tag，那么批次操作失败。如何设置批次确认为false，不能进行批次确认，需要单个确认。



结合redis的特性与basicNack()对一消息实现某特定次数的重试

basicNack(devliverTag,false,false); #第一个参数为不确认deliveryTag对应的消息，第二个参数是否应用于多消息，第三个参数是否requeue

https://www.freesion.com/article/8316583715/

requeue = true时，**消息会被无限次的放入消费队列重新消费**，

直至回送ACK 存在的问题：they will create a requeue/redelivery loop. Such loops can be costly in terms of network bandwidth and CPU resources

大量消耗带宽与cpu的资源



当requeue = false 的时候，**此时消息就会立马进入到死信队列**

//消费失败重试3次，3次失败后放入死信队列
            msgId = (String) message.getMessageProperties().getHeaders().get("spring_returned_message_correlation");
            int retryCount = (int) redisUtil.get(msgId);
            System.out.println("------ retryCount : " + retryCount);
            if (retryCount >= MAX_RECONSUME_COUNT) {
                //requeue = false 放入死信队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } else {
                //requeue = true 放入消费队列重试消费
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                redisUtil.set(msgId, retryCount + 1);
         }



如何rabbitmq如何避免消息的重复消费

消费者获取到消息后先根据id去查询redis/db是否存在该消息,如果不存在，则正常消费，消费完毕后写入redis/db,如果存在，则证明消息被消费过，直接丢弃

准备一个第三方存储,来做消费记录。以redis为例，给消息分配一个全局id，

只要消费过该消息，将<id,message>以K-V形式写入redis。那消费者开始消费前，先去redis中查询有没消费记录即可



















RabbitMQ中消费消息的两种模式的对比

Push模式：basic.consume

mq主动将消息推送给消费者（消费者需提供一个消费接口）
mq属于主动方，消费者属于一种被动消费，一旦有消息到达mq，会触发mq推送机制，将消息推送给消费者，不管消费者处于何种状态。
优点：
消费者代码较少：对于消费者来说，只需提供一个消费接口给mq即可；mq将接收到的消息，随即推送到指定的消费接口
消息实时性比较高：对于消费者来说，消息一旦到达mq，mq会立即推送给消费者
缺点：
消费者属于被动方，消息量比较大时，对消费者性能要求比较高；若消费者机器资源有限，可能会导致压力过载，引发宕机的情况。
对消费者可用性要求比较高：当消费者不可用时，会导致很push失败，在mq方需要考虑至少推送成功一次

Pull模式 ：basic.get

消息消费的过程：
消费端采用轮询的方式，从mq服务中拉取消息进行消费
消费完成通知mq删除已消费成功的消息
继续拉取消息消费
对于消费者来说，是主动方，可以采用线程池的方式，根据机器的性能来增加或缩小线程池的大小，控制拉取消息的速度，可以很好的控制自身的压力。
优点：
消费者可以根据自己的性能主动控制消息拉去的速度，控制自己的压力，不至于把自己弄跨
实时性相对于push方式会低一些
消费者属于主动方，控制权更大一些

消息消费模式的选择参考push模式与pull模式的特点来进行选择

消费者性能较好，对实时性要求比较高的，可以采用push的方式

消费者性能有限，建议采用pull的方式

整体上来说，主要在于消费者的性能，机器的性能如果没有问题，push和pull都是可以的



队列持久化，尽量少的减少信息的丢失

队列的声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果想重启之后还存在就要使队列持久化，<font color=red>保存到Erlang自带的Mnesia数据库中，当rabbitmq重启之后会读取该数据库。</font>>

 自动删除机制避免队列中消息的丢失

当队列中有消息时，无论是否排他(https://blog.csdn.net/hong10086/article/details/106738977)，<font color=red>关闭连接都不会删除队列，此时消费者消费完消息后再断开消费者，队列会被自动删除。（这里如果有多个消费者消费同一个队列，则需要所有消费者都断开后才能自动删除）</font>

搭建rabbitmq的集群避免消息的丢失

在生产环境下，一般都不会允许rabbitmq这种消息中间件单点，以免单点故障导致服务不可用，那么rabbitmq同样可以集群部署来保证服务的可用性，在rabbitmq集群中，我们可以定义HA队列，可以在web管理平台设置，也可以通过AMQP接口设置，当我们定义某个HA队列的时候，会在集群的各个节点上都建立该队列，发布消息的时候，直接发送至master服务，当master服务受到消息后，把消息同步至各个从节点，假如开启事务的情况下，是需要在消息被同步到各个节点之后才算完成事务，所以会带来一定的性能损耗，所以还是回到之前说的，性能和安全直接，需要根据实际业务的需要找到平衡点

![](D:\develop\report\image\rabbitmq-HAd队列.png)



服务提供方：publish confirm



