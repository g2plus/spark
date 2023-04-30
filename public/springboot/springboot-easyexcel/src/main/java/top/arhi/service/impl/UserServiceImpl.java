package top.arhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.arhi.domain.User;
import top.arhi.mapper.UserMapper;
import top.arhi.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public List<User> getUserList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().le("id", 1000);
        return userMapper.selectList(queryWrapper);
    }
}
