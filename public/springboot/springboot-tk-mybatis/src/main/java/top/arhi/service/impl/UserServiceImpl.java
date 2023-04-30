package top.arhi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import top.arhi.domain.User;
import top.arhi.mapper.UserMapper;
import top.arhi.service.UserService;

import java.util.List;


@Service
@Primary//(存在多个实现类时，使用此注解加载此bean到spring容器，启动实现类不做加载)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean save(User user) {
       return userMapper.insert(user)==1;
    }

    @Override
    public boolean delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id)==1;
    }

    @Override
    public boolean update(User user) {
        return userMapper.updateByPrimaryKey(user)==1;
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }



}
