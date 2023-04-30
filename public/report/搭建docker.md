![image-20211126183730193](C:\Users\e2607\AppData\Roaming\Typora\typora-user-images\image-20211126183730193.png)

参考链接地址：https://www.runoob.com/docker/centos-docker-install.html

docker search #搜索image

docker pull name #下来image（默认下载最新版本）

docker进行并创建容器

参考链接：[【快学Docker】快速创建容器，容器常用命令 - happyjava - 博客园 (cnblogs.com)](https://www.cnblogs.com/happy4java/p/11206853.html)

#### 新建并运行容器

命令如下：

```
docker run <image>
```

通过run命令创建的容器，默认是会运行的。

常用参数如下：

-i 让容器的标准输入保持打开

-t 分配一个伪终端

-d 容器处于守护进程运行

--name 设置容器的名字

-p 可以映射宿主机端口至容器端口，如 -p 8080:8081 ，左边为宿主机端口，右边为容器端口

-v 可以挂在宿主机目录至容器目录，如-v /data:/tmp/data，左边为宿主机目录，右边为容器目录。

如，一条实际运行容器的命令：

容器管理

查看运行着的容器

docker ps 

docker stop container_name #根据名称停止正在运行的容器 

docker start container_name #根据名称启动容器

#### 重启容器

```
删除容器
命令如下：

docker rm <container_id/name>
如果是正在运行的容器，会提示删除失败，可以通过 -f 参数强制删除。例如，删除正在运行的 my_ubuntu 容器：

docker rm -f my_ubuntudocker restart <container_name/id>
```

docker logs <id/name>

#### docker exec 命令

docker exec 本质上不是连接容器，而是在容器中执行命令，其用法如下：

```
docker exec -it <container_name/id> <exec>
```

其中<exec>是需要执行的命令，如在my_ubuntu容器下执行 /bin/bash 命令：

```
docker exec -it my_ubuntu /bin/bash
```

