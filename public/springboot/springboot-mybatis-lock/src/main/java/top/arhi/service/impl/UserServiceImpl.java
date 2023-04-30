package top.arhi.service.impl;

import com.github.pagehelper.Page;
import org.springframework.context.annotation.Primary;
import top.arhi.dao.UserMapper;
import top.arhi.domain.User;
import top.arhi.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Primary//(存在多个实现类时，使用此注解加载此bean到spring容器，启动实现类不做加载)
public class UserServiceImpl implements User2Service {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean save(User user) {
       return userMapper.save(user)==1;
    }

    @Override
    public boolean delete(Integer id) {
        return userMapper.delete(id)==1;
    }

    @Override
    public boolean update(User user) {
        return userMapper.update(user)==1;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
