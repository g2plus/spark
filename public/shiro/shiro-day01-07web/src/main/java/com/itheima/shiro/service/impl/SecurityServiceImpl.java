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

        return DigestsUtil.entryptPassword("@12345678a");
    }

    @Override
    public List<String> findRoleByLoginName(String loginName) {
        List<String> list = new ArrayList<>();
        if ("admin".equals(loginName)){
            list.add("admin");
        }
        list.add("dev");
        return list;
    }

    @Override
    public List<String> findPermissionByLoginName(String loginName) {
        List<String> list = new ArrayList<>();
        if ("jay".equals(loginName)){
            list.add("order:add");
            //添加权限，但是不是管理员因此无法查看。
            list.add("order:list");
            list.add("order:del");
        }
        return list;
    }
}
