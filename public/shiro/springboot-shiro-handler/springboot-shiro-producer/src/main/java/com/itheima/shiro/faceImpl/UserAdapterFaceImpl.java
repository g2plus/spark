package com.itheima.shiro.faceImpl;

import com.itheima.shiro.adapter.UserAdapter;
import com.itheima.shiro.face.UserAdapterFace;
import com.itheima.shiro.pojo.Resource;
import com.itheima.shiro.pojo.Role;
import com.itheima.shiro.pojo.User;
import com.itheima.shiro.utils.BeanConv;
import com.itheima.shiro.utils.EmptyUtil;
import com.itheima.shiro.vo.ResourceVO;
import com.itheima.shiro.vo.RoleVO;
import com.itheima.shiro.vo.UserVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description：用户资源、角色、用户信息服务接口实现
 */
@Service(version = "1.0.0", retries = 3,timeout = 5000)
public class UserAdapterFaceImpl implements UserAdapterFace {

    @Autowired
    UserAdapter userAdapter;

    @Override
    public UserVO findUserByLoginName(String loginName) {
        User user = userAdapter.findUserByLoginName(loginName);
        if (!EmptyUtil.isNullOrEmpty(user)){
            return BeanConv.toBean(user, UserVO.class);
        }
        return null;
    }

    @Override
    public List<RoleVO> findRoleByUserId(String userId) {
        List<Role> list = userAdapter.findRoleByUserId(userId);
        if (!EmptyUtil.isNullOrEmpty(list)){
            return BeanConv.toBeanList(list, RoleVO.class);
        }
        return null;
    }

    @Override
    public List<ResourceVO> findResourceByUserId(String userId) {
        List<Resource> list = userAdapter.findResourceByUserId(userId);
        if (!EmptyUtil.isNullOrEmpty(list)){
            return BeanConv.toBeanList(list, ResourceVO.class);
        }
        return null;
    }
}
