package cn.tanhua.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
/**
 * 允许开启异步线程池
 */
@EnableAsync
public class DubboMongoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboMongoApplication.class,args);
    }
}
