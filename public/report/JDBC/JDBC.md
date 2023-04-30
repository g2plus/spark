JDBC的本质：使用由sun公司提供的接口，数据库库厂商实现接口的jar包，使用接口进行编写，执行的是jar包中的实现类（面向接口编程）

实现的一般步骤：

1.导入驱动jar包

2.注册驱动

3.获取数据库连接对象Connection

4.定义sql

5.获取执行sql语句的statement对象

6.执行sql，接收放回结果

7.处理结果

8.释放资源

创建连接对象详解

DriverManager：驱动管理对象

​	功能：告诉程序使用哪个数据驱动jar包

​	static void registerDriver(Driver driver)

​	Class.forName("com.mysql.jdbc.Driver");

```
static {
    try {
        DriverManager.registerDriver(new Driver());
    } catch (SQLException var1) {
        throw new RuntimeException("Can't register driver!");
    }
}
```

在mysql5之后的版本可以省略注册驱动步骤。

Connection：数据库连接对象

​	功能：获取执行sql的对象

​				createStatement();

​				prepareStatement();

​				进行事务的管理

​						开启事务 setAutoCommit();

​						提交事务：commit();

​						回滚事务：rollback();

statement: 执行sql的对象

​	executeUpdate(String sql); 执行DML(insert，update，delete)语句，DDL(create，alter，drop)操作数据库database，数据表table

ResultSet：结果集对象

​		next() 返回获取下一行数据

PreparedStatement：执行sql的对象。

JDBCUtils

 使用工具类思想，进行代码封装提高代码的复用性。

创建连接

释放资源，

静态代码块的作用

权限修饰符的作用



数据库连接池的作用：

避免了连接的反复创建与关系，连接池的close方法将连接归还给连接池，实现连接的复用

C3P0

1.导入c3p0的maven坐标

2.在src目录下配置c3p0-config.xml

3.使用new ComboPooledDataSource()创建连接池对象

c3p0使用的参考链接地址

https://blog.csdn.net/zhanghanlun/article/details/80918422

https://zhidao.baidu.com/question/2010876545802375788.html

druid数据库连接池的使用参考

https://www.cnblogs.com/chy18883701161/p/12594889.html

druid连接池一般采用properties配置文件，c3p0推荐使用xml配置文件，文件的名称必须为c3p0-config.xml; druid与c3p0都可以使用代码进行设置具体的配置信息。



避免sql注入使用preparedStatement

```
connection = DruidUtil.getConnection();
String sql = "insert into account (id,name,balance) values (1,?,?)";

preparedStatement = connection.prepareStatement(sql);
//给占位符？进行赋值
preparedStatement.setString(1,"wzm");
preparedStatement.setInt(2,3000);
//使用无参方式执行
preparedStatement.executeUpdate();
```

spring框架的jdbcTemplate的使用

![](D:\develop\report\image\jdbc使用需要导入的jar包.png)

重要方法

update() 执行DML语句，增删改

queryForMap() 查询结果封装为一个map集合 （结果以key=value的形式展示）；只能存储数据库中的一条记录

queryForList() 查询结果封装为一个list集合 如果要存存储多条记录，使用list

<font color=red>query() 查询结果，封装为javabean对象 实现类 BeanPropertyRowMapper<T>(T.class)</font>

//queryForObject() 查询结果，将结果封装为对象