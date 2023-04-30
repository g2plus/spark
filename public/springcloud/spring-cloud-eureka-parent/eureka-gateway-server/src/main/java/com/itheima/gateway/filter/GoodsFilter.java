package com.itheima.gateway.filter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * !!!声明为一个Component,进行统一的网关过滤处理
 */
@Component
public class GoodsFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Gateway的全局过滤器......");
        //放行请求
        return chain.filter(exchange);
    }


    /***
     * 过滤器排序,与其他过滤器进行比较,
     * 返回数字小的优先被执行
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
