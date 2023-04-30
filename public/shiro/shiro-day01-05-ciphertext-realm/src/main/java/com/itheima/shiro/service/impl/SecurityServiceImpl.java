package com.itheima.shiro.service.impl;

import com.itheima.shiro.service.SecurityService;
import com.itheima.shiro.tools.DigestsUtil;

import java.util.Map;

/**
 * @Description：模拟数据库操作服务接口实现
 */
public class SecurityServiceImpl implements SecurityService {

    @Override
    public Map<String,String> findPasswordByLoginName(String loginName) {
        //模拟用户注册时候，添加到数数据库的盐及密码。
        return DigestsUtil.entryptPassword("123");
    }
}
