package com.itheima.shiro.service.impl;

import com.itheima.shiro.service.SecurityService;
import com.itheima.shiro.tools.DigestsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description：模拟数据库操作服务接口实现
 */
public class SecurityServiceImpl implements SecurityService {

    @Override
    public Map<String,String> findPasswordByLoginName(String loginName) {

        return DigestsUtil.entryptPassword("123");
    }

    /*模拟查询数据获取当前登录用户的角色*/
    @Override
    public List<String> findRoleByLoginName(String loginName) {
        List<String> list = new ArrayList<>();
        list.add("admin");
        list.add("dev");
        return list;
    }

    /*模拟查询数据获取当前登录用户的权限 权限设置的一般规制业务:功能*/
    @Override
    public List<String> findPermissionByLoginName(String loginName) {
        List<String> list = new ArrayList<>();
        list.add("order:add");
        list.add("order:list");
        list.add("order:del");
        return list;
    }
}
