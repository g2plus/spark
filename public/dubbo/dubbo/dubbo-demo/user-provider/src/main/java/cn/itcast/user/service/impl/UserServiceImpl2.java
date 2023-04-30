package cn.itcast.user.service.impl;


import cn.itcast.user.domain.User;
import cn.itcast.user.mapper.UserMapper;
import cn.itcast.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

//暴露dubbo服务，
@DubboService(version = "2.0.0")
public class UserServiceImpl2 implements UserService {

    @Autowired
    private UserMapper userMapper;

    public String queryUsername(Long id) {
        return userMapper.findById(id).getUsername();
    }

    @Override
    public User queryById(Long id) {
        User user = userMapper.findById(id);
        user.setUsername(user.getUsername()+"v2.0");
        return user;
    }
}