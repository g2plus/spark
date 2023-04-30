mysql5.7安装参考教程

https://dev.mysql.com/downloads/file/?id=507350

https://www.jianshu.com/p/a41d45890ab2

linux版本

https://downloads.mysql.com/archives/get/p/23/file/mysql-5.7.29-1.el7.x86_64.rpm-bundle.tar



my.ini配置

[client]
default-character-set = utf8mb4
[mysql]
default-character-set = utf8mb4
[mysqld]

port=3306
character-set-client-handshake = FALSE

character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
init_connect='SET NAMES utf8mb4'

basedir=E:\develop\MySQL

datadir=E:\develop\MySQL\data

max_connections=1000

default-storage-engine=INNODB
lower_case_table_names=1
max_allowed_packet=104857600

sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES

#### The user specified as a definer ('root'@'%') does not exist

###### [(1条消息) The user specified as a definer ('root'@'%') does not exist_罗小树的博客-CSDN博客](https://blog.csdn.net/u010999809/article/details/87806126#:~:text=The user specified as a definer ('root'%40'%') does,privileges%3B --刷新权限 解释： ALL PRIVILEGES：当前用户的所有权限 .. 2019独角兽企业重金招聘Python工程师标准>>> )

修改密码

登录账户

设置允许远程机器登录MySQL服务

GRANT` `ALL` `PRIVILEGES` `ON` `*.* ``TO` `'root'``@``'%'``IDENTIFIED ``BY` `'rootAccoutPassword'` `WITH` `GRANT` `OPTION

https://www.cnblogs.com/young233/p/10829000.html

GRANT ALL PRIVILEGES ON * .* TO 'root'@'%' IDENTIFIED BY 'youpassword' WITH GRANT OPTION;

windows端使用管理员权限进行启动与停止mysql服务

net stop mysql

net start mysql

mysql 的本地登录

使用navicat进行操作。

mysql -uroot -p

enter password：

---------------------------------------------------------------------------------------------------------------------------------------------------------------------



SQL分类



DDL: 数据库，数据表的操作。create ,alter drop

DML:数据表中数据记录的增删改 insert delete update

DQL:数据表中数据记录的查询 select

DCL:授权控制  grant,revoke



DDL:

数据库操作

查询存在的数据库

show databases；#查看存在的数据库

show create database mysql； #查询数据库的采用的编码方式

新建数据库

create database if not exists db;# 如果db数据不存在则创建数据库

create database if not exists db character set utf8；

修改数据库的字符

alter database dbname character set utf8；

删除数据库

drop database if exists dbname；#如果数据库存在进行删除

查询正在使用的数据库

select database(); #查询正在使用的数据库

使用数据库

use databasename；





操作数据表

查询已存在的数据表

show tables；

查看数据表的描述信息

desc tablename；

eg：use mysql；show tables；desc host；

数据表重命名

ALTER TABLE netbian RENAME TO netbianbak;

数据表之间的copy

INSERT INTO netbian (website,url) SELECT website,url FROM netbianbak



创建数据表

create table tablename(

​	column_name1 dataType,

​	column_name2 dataType

);

数据类型：

字符 char 与varchar(len)

len-限定字符的长度，如果超过len，报错，无法进行设置。

整数 int

小数 double(4,1)小数点后支持1位 --支持最大存储小数值为100.0

日期 date yyyy-MM-dd

datetime yyyy-MM-dd HH:mm:ss

<font color=red>时间戳： 如果将来不给这个字段进行赋值，或者赋值为null，则默认使用当前系统时间。</font>

使用mysql进行存储文本使用

tinytext<text<mediumtext<longtext (文本多媒体系统一般不存储在数据库)

图片上传到fastdfs，保存图片的url地址。

参考连接：https://www.runoob.com/mysql/mysql-data-types.html

数据表进行复制

create table tablename like copied_tablename；

修改数据表

修改数据表的名称

alter table studentbak rename to teacher;

查看数据表的编码

show create table teacher；

修改数据表的编码

alter table character set utf8；

<font color=red>添加字段</font>

alter table tablename add column_name dataType;

例如

alter table teacher add gender varcha(10);

<font color=red>修改字段名称</font>

alter table teacher change old new varchar(20); #修改字段名称与数据支持长度（数据类型不可省略，否则语法错误）

alter table teacher modify sex varchar (10); #进修改数据支持长度（仅仅用来修改数据的类型）

<font color=red>删除列字段</font>

alter table tablename drop column_name;

删除数据表

drop table if exists tablename；

-------------------------------------------------------------------------------------------------------------------------------------------

DML

操作数据记录，读数据记录进行添加，修改，删除。

添加操作

```sql
INSERT INTO table_name ( column1, column2,...columN )
                       VALUES
                       ( value1, value2,...valueN );#用来添加一条记录，添加多条记录，记录与记录之间采用逗号进行分隔
```

values 与 value 的区别在mysql里面没有区别，字段列表必须添加（）

添加单条记录使用values，添加多条使用value https://blog.csdn.net/weixin_43889681/article/details/92672514?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-2.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-2.no_search_link

value的数据类型与column的类型匹配，

字符串使用''(单引号/双引号)，日期使用使用'yyyy-MM-dd'

如果设置主键id，且id自动增长，id类型为bigint，或者int类型时，

可以不知此字段的之间进行插入



例如

create table headteacher(
	id int(11) not null AUTO_INCREMENT primary key,
	`name` varchar(20)
);
insert into headteacher (name) values ('zhangsan'),('lisi');
select id,name from headteacher;



 <font color=red>进行修改更新操作</font>

update table_name set column=value where column=value;

例如

update teacher set name = 'top' where name = 'arhi' and age is NULL;

特别注意：当某个字段的值没有进行设置时，默认值为null（空） 空判断使用关键值 is/is not，

select * from teacher where teacher.gender='female' and teacher.married='no' or teacher.married is NULL;



<font color=red>删除记录</font>

delete  from table_name where 条件 #记得添加where 条件

切记修改与删除记得添加条件，否则多条数据进行了修改。



清空数据表的记录

truncate table table_name #清空所有数据，创建一个空表 （清空所有数据的效率更高）

-------------------------------------------------------------------------------------------------------------------------------------------

DQL

1.查询语句

select 字段列表 from 表名列表 where 条件列表 group by 分组字段 order by 排序 limit 分页限定

列表的两侧不使用(), 字段列表使用报错信息为Operand should contain 1 column(s)

两个不同字段之间使用逗号进行分隔，字段与字段的别名之间使用关键字AS或则空格。

基础查询

多字段查询(column1,column2)，去重查询(distinct)-去除重复的结果集，计算列count(column)，起别名AS

insert into student (name,age,math,english,address) values ('zhangsan',23,99.0,80.0,'hunan'),('lisi',23,99.0,80.0,'hunan'),
('wangwu',23,NULL,80.0,'hunan'),('YUTING',23,NULL,NULL,'hunan'),('YUTING',23,100.0,100.0,'GUANGDONG');

select * from student;


select name,math,english,(IFNULL(math,0)+IFNULL(english,0)) as sum,address from student;

select math,en,ifnull(math,0)+ifnull(en,0) as sum from student;

进行查询时应该避免的坑

()内不能放两个字段；

IFNULL(expression1，expression2) 聚合函数的作用 --当exprression1的值为NULL时,转换expression2代替。

NULL的特点：与NULL进行计算结果为null。

起别名的字段的原字段可以使用()

-------------------------------------------------------------------------------------------------------------------------------------------

MySQL中的运算符

大于 > 

小于 <

!=

=

大于等于>=

小于等于<=

范围 BETWEEN ... AND...

模糊查询 LIKE： _ 单个任意字符，%多个任意字符

IS NULL

AND OR NOT

---------------------------------------------------------------------------------------------------------------------------------------------------------------------



排序查询

语法：order by column asc

eg：order by math desc，English desc；

asc：默认

desc

如果多个字段进行排序，如果前一个字段的值相同，才会判断第二个条件

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



聚合函数

1.count() --计数 -- 在开发中一般根据主键来进行统计个数

2.sum() --求和

3.max() --求最大值

4.min() --求最小值

5.avg() --求平均值

<font color=red>聚合函数的特点：</font>

```
1.聚合函数aggregation function又称为组函数。默认情况下 聚合函数会对当前所在表当做一个组进行统计
2.每个组函数接收一个参数（字段名或者表达式）统计结果中默认忽略字段为NULL的记录
3.要想列值为NULL的行也参与组函数的计算，必须使用IFNULL函数对NULL值做转换。
4.不允许出现嵌套 比如sum(max(xx)) --报错详情：Invalid use of group function
```

使用案例

select count(math) from student;
select math from student;
select sum(math) from student;
select avg(IFNULL(math,0)) from student; -- select sum(math)/count(IFNULL(math,0)) as avg from student;

如果某个学生的成绩为空时，应该进行处理。

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



分组查询

![](D:\develop\report\image\分组查询.png)

语法：group by 字段；

注意：

1.分组之后的字段，分组字段，聚合函数(分组字段)有效

2.where 与 having的区别

where在分组之前进行限定，不满足条件，不参与分组

having 在分组之后进行下定，不满足条件，则不会查询出来

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



分页查询

limit：开始的索引，每页查询的条数（跳过n条记录，查询m条）

select * from teacher limit 0，3；

select * from teacher limit 3，3；

select * from teacher limit  6，3；

开始索引计算公式（page-1）*每页条数 。

pageHelper的引入， mybatis分页。 springboot - pagehelper

-------------------------------------------------------------------------------------------------------------------------------------------



约束

非空约束

not null

创建数据表时添加非空约束

create table student(

​	id int,

​	name varchar(20) not null;

);

创建表后添加非空约束

alter table table_name modify column not null;

去除非空约束

alter table name modify  column；

非空约束失效，此时添加数据，如果某个字段为null，则进行数据的填充后，再次设置此字段为非空约束，此时设置失败。





主键约束

一个数据表只存在一个主键，

主键不能为null，主键的值唯一

create table student(

​	int id primary key auto_increment, -- 设置主键自增(此场景下，不能将primary key删除)

​	name varchar(20) not null

);

删除主键

alter table table_name drop primary key;

没有主键的表，添加主键，确保添加主键的列的内容不能为空。

alter table table_name  modify cloumn 数据类型 primary key

例如：

alter table teacher modify id int(11) primary key;



唯一约束

创建表时指定唯一索引

create table table_name(

​		name varchar(20),

​		age int(11),

​		gender varchar(20),

​		id int(11) unique;

);

<font color=red>唯一索引：支持null值,允许设置多个字段</font>

创建表时，没有创建唯一索引，再次创建的操作

alter table table_name modify column 数据类型 unique；

删除唯一索引的方法

alter table table_name drop index cloumn

多表之间的关系

外键关联

#从表

create table employee(

​	...,

​	外键列 数据类型，-- 对应主表的字段数据类型与主表设置保持一致。

​	constraint 外键名称 foreign key 外键列 references 主表名称(外表列名称)

);

创建外键的步骤

外键：
1、要求在从表设置外键关系
2、从表的外键列的类型和主表的关联列的类型要求一致或兼容，名称无要求
3、主表的关联列必须是一个key（一般是主键或唯一）
4、插入数据时，先插入主表，再插入从表
删除数据时，先删除从表，再删除主表

drop table emloyee;
create table employee(
	id int(11) primary key,
	`name` varchar(20),
	age int(11),
	department_id int(11),
	CONSTRAINT dep_id foreign key (department_id) references department(id)
);
select * from employee;
insert into employee (id, name,age,department_id) value (1,'zhangsan',23,1),(2,'lisi',24,2),(3,'wangwu',25,1);
explain select employee.name,employee.name,department.department_name,department.address from employee join department on employee.department_id=department.id;
explain select employee.name,employee.name,department.department_name,department.address from employee,department where employee.id=department.id;
select employee.name,employee.name from employee where id in(1,2,3);

update employee set department_id=null where department_id=1;
update department set id = 12 where id = 1;
update employee set department_id=12 where department_id is null;

删除外键：

alter table table_name drop foreign key 外键名称

添加外键

alter table table_name add constraint 外键名称 foreign key 外键列 references 主表名称(主表列名称一般是主键或唯一)

级联：

1.更改从表的foreign key字段的值为null

2.修改主表的id

3，修改从表的字段为新值。

在where条件判断判断是否为null，必须使用null，

字段设置值为null，请使用set column=null。

一对多：建立外键，外键建在多表的一方

![](D:\develop\report\image\级联操作.png)

<font color=red>级联删除特别注意：删除主表数据，从表记录会被删除</font>

-------------------------------------------------------------------------------------------------------------------------------------------

多表之间的关系

一对一：

一对多：

多对多：(多对多关系需要使用一个中间关系表)

范式(为了更好的建表)

三大范式：

1NF: 数据表中的每一列（每个字段）必须是不可拆分的最小单元，也就是确保每一列的原子性

2NF: 满足1NF后，要求表中的所有列，都必须依赖于主键，而不能有任何一列与主键没有关系，也就是说一个表只描述一件事情；

3NF:必须先满足第二范式（2NF），要求：表中的每一列只与主键直接相关而不是间接相关，（表中的每一列只能依赖于主键）；

-------------------------------------------------------------------------------------------------------------------------------------------

数据库的备份与还原（非常重要）

命令式导出数据库

备份：mysqldump -u用户名 -p密码 数据库名称（db1）> 保存的路径(d://db.sql) 

还原

​	1.登录数据库 

 2. 创建数据库 create database if not exists database_name；

 3. 使用数据库  use database_name；

 4. 执行文件 source  文件路径。source d://db.sql;

    ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    多表查询（实践）

    笛卡尔积（求集合a，b的所有组合情况）——》可能导致无用数据的问题

    多表查询的分类：

    内连接：

    隐式内连接：select table1.column,table2.column from table1,table2 where table1.id=table2.id; （实际开发中隐式使用的频次更高）

    显式内连接： select table1.coulumn，table2.column from table1 inner join table2 on table1.id = table2.id；<显式内连接：采用关键字on进行进行条件的关联>

    三个因素：数据来源-数据表

    ​					数据字段

    ​					条件

    外连接：

    ​	左外连接：select table1.column,table2.column from table1 left join table2 on table1.id=table2.id;

    ​					查询：查询左表的所有记录及与其有交集的部分。(使用left join在开发中的频次更高)

    ​	右外连接: right 

    子查询：

    ​		数据结果作为条件（=，in）

    ​		数据结果作为虚拟表 别名 （as或者空格）

    在条件判断中，时间上的判断可以采用基本运算符如>,<,>=,<=,!=

    数据查询练习题目：牛客网。

    取模函数

    MOD(column, 2)=1

    查询奇数的正则化方法：emp_no REGEXP ‘[13579]![img](https://www.nowcoder.com/equation?tex=%E2%80%99%0A%E6%9F%A5%E8%AF%A2%E5%81%B6%E6%95%B0%E7%9A%84%E6%AD%A3%E5%88%99%E5%8C%96%E6%96%B9%E6%B3%95%EF%BC%9Aemp_no%20REGEXP%20%E2%80%98%5B02468%5D&preview=true)’

    窗口函数，排序

    dense_rank() over (order by salary desc) as rank

    -- rank排名：查询表中大于自己薪水的员工的数量（考虑并列：去重）

    -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    必须学会的一种。

    SELECT 
    s1.emp_no,
      s1.salary,
    (SELECT 
        COUNT(DISTINCT s2.salary) 
    FROM
        salaries s2 
    WHERE s2.to_date = '9999-01-01' 
        AND s2.salary >= s1.salary) AS `rank`  -- 去重：计算并列排名   FROM
    salaries s1 
    WHERE s1.to_date = '9999-01-01' 
ORDER BY s1.salary DESC,
      s1.emp_no ;

    

    窗口函数某些数据库不支持

    SELECT 
    emp_no,
      salary,
    dense_rank () over (
    ORDER BY salary DESC) AS `rank` 
FROM
      salaries 
WHERE to_date = '9999-01-01' ;
    
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

DCL:用户管理与权限控制

1.添加用户信息
	use mysql #使用mysql数据表
    select * from user #查询user表
    create user ‘username’@‘ip’ identified by ‘password’；
    create user ‘username’@'%' identified by 'password'

2.删除用户
​	drop user  ‘username’@‘ip’

3.修改用户用户密码
    

    	update user set password=password('newpassword') where user='username';
    
    ​	MySQL忘记了root用户密码，如何从新登录
    
    ​	1.使用管理员停止mysql服务
    
    ​	2.使用无验证方式启动mysql服务：mysqld --skip-grant-tables
    
    ​	3.打开新的cmd窗口，直接输入mysql命令，回车登录
    
    ​	4.use mysql；
    
    ​	5.update user set password = password('newpassword') where user='root';
    
    ​	6.关闭两个窗口
    
    ​	7.启任务服务器，结束mysqld.exe
    
    ​	8.启动mysql服务
    
    ​	9.使用新密码登录。
    
    4.权限授予
    
    ​	查看用户的权限 show grants for ‘用户名’@'主机名'
    
    ​	添加权限 grant 权限列表 on 数据库名.表名 to ‘用户名’@‘主机名’
    
    ​	给某用户授予所有的权限 grant all on 星号.星号 to ‘username’@‘ip’
    
    5.收回权限
    
    ​	revoke 权限列表 on 数据库名.表名 from ‘用户名’@‘主机名’
    
    --------------------------------------------------------------------------------------------------------------------------------


事务（实践）

 mysql的事务四大特征

acid：

原子性（atomicity）：事务全部执行，或全部不执行

一致性（consistency）：事务从一种正确状态切换到另一正确状态

隔离性（isolation）：不同事务之间不能进行共享

持久性（durability）：事务正确提交后，其结果永久保存在数据库中。事务提交前后，数据总量不变

 @transactional的作用：https://blog.csdn.net/qq_41517071/article/details/96282332  











mysql数据库的中事务默认自动提交

​	事务提交的两种方式

​		自动提交：

​				mysql就是自动提交：

​						一条xml（增删改）语句会自动提交一次事务。autocommit 默认为1

​				手动提交：

​						需要先开启事务，再提交

​	修改事务的默认提交方式

​		查看事务的默认提交方式：SELECT @@autocommit； 1 代表自动提交  0 代表手动提交

​		修改默认提交方式：SET @@autocommit = 0;

​		手动commit；查询的结果为修改后的数据。

​       可以使用windows端的命令进行执行。

特别提醒

​		oracle的事务需要手动进行提交。

事务的隔离级别

​	概念：多个事务之间隔离的，相互独立。但是如果多个事务操作同一批数据，则会引发一些问题，设置不同的隔离级别就可以解决这些问题。

​	存在问题：

​	1.脏读：一个事务，读取到另一事务中没有提交的数据。

	2. 不可重复读(虚读)：在同一事务中，两次读取到的数据不一样
	3. 幻读：一个事务操作（dml（增删改））数据表中的所有记录，另一事务添加了一条数据，则第一个事务查询不到自己的修改

隔离级别：（1.最低级别，4.最高级别）

​	1.read uncommitted：读未提交

​			产生的问题：脏读，不可重复读，幻读

​	2.read committed：读已提交（oracle的默认事务级别）

​			产生的问题：不可重复读，幻读；

​	3.repeatable read：可重复读（mysql默认的事务级别）

​		产生的问题：幻读

4. serializable：串行化（锁表）

   解决所有问题

注意点：隔离级别从小到大安全性越来越高，但是效率越来越低

数据库查询隔离级别

​	select @@tx_isolation

数据库设置隔离级别

​	set global transaction isolation level 级别字符串

例如

​	set global transaction isolation level read committed；（设置后，断开连接，再次连接，查询发现已修改）

一个请求进行了update的操作，如何确保另一请求查询到的数据为最近的数据

select * from account for update；

mysql的innodb锁机制：[mysql共享锁与排他锁 - java攻城狮 - 博客园 (cnblogs.com)](https://www.cnblogs.com/boblogsbo/p/5602122.html)

You can't specify target table 'pi_property_contract_det' for update in FROM clause
错误代码 1093
解决方法
select出的结果再通过中间表select一遍






