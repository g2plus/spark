1.安装过程

在/usr/local/develop下创建文件

mkdir svn

yum install -y subversion

svnadmin  create ./svn/repo

ls  #查看repo目录

修改配置文件 authz

1.添加

[/]

arhi = rw

修改配置文件 passwd

1.添加用户与密码

修改配置文件svnserve.conf

1.anon-access 

2.auth-access

3.password-db = passwd

4.authz-db=authz

5.realm = My First Repository 

2.开机自启动

参考连接:https://blog.csdn.net/kenhins/article/details/74575458(推荐采用方式一)

参考连接:https://www.cnblogs.com/-mrl/p/8980244.html(版本库目录与版本库)

