package com.itheima.consumer.feign;


import com.itheima.consumer.config.FeignLogConfig;
import com.itheima.consumer.domain.Goods;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="EUREKA-CLIENT-PROVIDER",configuration = FeignLogConfig.class)
/**
 * @Component 接口上面不能使用@Bean,@Component,@Service,@Controller注解,接口不能创建对象
 * 创建对象原理:jdk的动态代理
 */
public interface GoodsFeignClient {

    @GetMapping("/goods/findOne/{id}")
    /**
     * 动态代理
     */
    Goods findGoodsById(@PathVariable("id") int id);

}
