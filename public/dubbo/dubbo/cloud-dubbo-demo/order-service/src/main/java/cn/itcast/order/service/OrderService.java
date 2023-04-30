package cn.itcast.order.service;


import cn.itcast.dubbo.domain.Order;

public interface OrderService {

    Order queryOrderById(Long orderId);
}
