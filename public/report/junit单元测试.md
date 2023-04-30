单元测试junit

常使用的注解 

@Before ：用来初始化资源

@After：用来做资源的释放

@Test：声明这个方法是一个测试方法

@Test的使用注意事项：方法访问权限修饰符必须为public，测试方法不能时静态方法

public：外部可以访问。

常用的类-Assert断言（官方推荐使用static方式进行导入）

assertEquals方法

参数expected，实际值actul

使用规范

1.测试类的命名规范--一般采用XxxTest类名

2.测试类的方法命名规范 -- 一般采用testXxx

在idea中测试方法参数输入进行输入数据进行测试需要在bin目录下的两个配置文件vmoptions文件中添加以下内容

-Deditable.java.test.console=true

