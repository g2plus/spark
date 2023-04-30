package com.itheima.shiro.core.bridge;

import com.itheima.shiro.vo.FilterChainVO;

import java.util.List;

/**
 * @Description：自定义过滤器桥接接口
 */
public interface FilterChainBridgeService {

    /**
     * @Description 查询所有有效的过滤器链
     */
    List<FilterChainVO> findFilterChainList();
}
