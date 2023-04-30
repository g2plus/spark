package com.itheima.shiro.face;

import com.itheima.shiro.vo.FilterChainVO;

import java.util.List;

/**
 * @Description：过滤器链服务化接口
 */
public interface FilterChainFace {

    /**
     * @Description 查询所有有效的过滤器链
     */
    List<FilterChainVO> findFilterChainList();
}
