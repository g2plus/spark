package com.itheima.consumer.controller;


import com.itheima.consumer.domain.Goods;
import com.itheima.consumer.feign.GoodsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 服务的调用方
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private GoodsFeignClient goodsFeignClient;

    /*@GetMapping("/goods/{id}")
    public Goods findGoodsById(@PathVariable("id") int id){
        System.out.println("findGoodsById..."+id);


        *//*
            //远程调用Goods服务中的findOne接口
            使用RestTemplate
            1. 定义Bean  restTemplate
            2. 注入Bean
            3. 调用方法
         *//*

        *//*
            动态从Eureka Server 中获取 provider 的 ip 和端口
             1. 注入 DiscoveryClient 对象.激活
             2. 调用方法


         *//*

        //演示discoveryClient 使用
        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-CLIENT-PROVIDER");

        //判断集合是否有数据
        if(instances == null || instances.size() == 0){
            //集合没有数据
            return null;
        }

        ServiceInstance instance = instances.get(0);
        //获取ip
        String host = instance.getHost();
        //获取端口
        int port = instance.getPort();

        System.out.println(host);//应用设置的ip-address
        System.out.println(port);//8001

        String url = "http://"+host+":"+port+"/goods/findOne/"+id;
        // 3. 调用方法
        Goods goods = restTemplate.getForObject(url, Goods.class);

        return goods;
    }*/



    /*//使用Ribbon简化RestTemplate的请求
    //1.RestTemplate的配置类方法中添加@LoadBalanced注解
    //2.使用服务提供客户端的应用名称进行url拼接
    @GetMapping("/goods/{id}")
    public Goods findGoodsById2(@PathVariable("id") int id) {
        String url = "http://EUREKA-CLIENT-PROVIDER/goods/findOne/"+id;
        Goods goods = restTemplate.getForObject(url, Goods.class);
        return goods;

    }*/

    @GetMapping("/goods/{id}")
    public Goods findGoodsById3(@PathVariable("id") int id){
        Goods goods = goodsFeignClient.findGoodsById(id);
        return goods;
    }
}
