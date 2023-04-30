docker search rabbitmq

docker pull rabbitmq

正确启动docker容器的方法

docker rm rabbitmq #移除某个服务

docker run -d -p5672:5672 -p15672:15672 --name rabbitmq rabbitmq:management

