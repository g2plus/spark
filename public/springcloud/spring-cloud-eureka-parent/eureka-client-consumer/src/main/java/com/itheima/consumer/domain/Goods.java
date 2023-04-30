package com.itheima.consumer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

    private int id;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品价格
     */
    private double price;
    /**
     * 商品库存
     */
    private int count;
}
