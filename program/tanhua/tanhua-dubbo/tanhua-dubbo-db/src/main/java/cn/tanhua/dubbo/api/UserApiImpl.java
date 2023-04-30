package cn.tanhua.dubbo.api;

import cn.tanhua.dubbo.mapper.UserMapper;
import cn.tanhua.model.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DubboService
public class UserApiImpl implements UserApi {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByMobile(String phone) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",phone);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Long save(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    @Override
    public User findByUserId(Long userId) {
        return userMapper.selectById(userId);
    }


    @Override
    public List<User> findAllUser(){
        return userMapper.finAllUser();
    }

}
