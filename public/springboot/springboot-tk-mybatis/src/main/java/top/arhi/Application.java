package top.arhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * 引导类。 SpringBoot项目的入口
 * 默认只能扫此文件所在的包。
 * 即如果在top.arhi下创建app文件，并放入此文件
 * 需要添加一个注解扫描
 * @ComponentScan("top.arhi")
 */
@SpringBootApplication
@MapperScan("top.arhi.mapper")
@org.mybatis.spring.annotation.MapperScan("top.arhi.mapper")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

}
