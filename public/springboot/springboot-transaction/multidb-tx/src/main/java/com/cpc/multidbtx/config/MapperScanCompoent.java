package com.cpc.multidbtx.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@MapperScan("com.cpc.multidbtx.mapper")
@Component
public class MapperScanCompoent {
}
