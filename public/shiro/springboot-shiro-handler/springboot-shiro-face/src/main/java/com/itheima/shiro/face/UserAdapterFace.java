package com.itheima.shiro.face;

import com.itheima.shiro.vo.ResourceVO;
import com.itheima.shiro.vo.RoleVO;
import com.itheima.shiro.vo.UserVO;

import java.util.List;

/**
 * @Description：用户服务接口定义
 */
public interface UserAdapterFace {

    /**
     * @Description 按用户名查找用户
     * @param loginName 登录名
     * @return
     */
    UserVO findUserByLoginName(String loginName);

    /**
     * @Description 查找用户所有角色
     * @param userId 用户Id
     * @return
     */
    List<RoleVO> findRoleByUserId(String userId);

    /**
     * @Description 查询用户有资源
     * @param userId 用户Id
     * @return
     */
    List<ResourceVO> findResourceByUserId(String userId);

}
