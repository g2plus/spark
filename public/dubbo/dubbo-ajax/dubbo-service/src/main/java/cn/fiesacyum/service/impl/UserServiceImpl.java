package cn.fiesacyum.service.impl;

import cn.fiesacyum.dao.UserDao;
import cn.fiesacyum.pojo.User;
import cn.fiesacyum.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public Boolean add(User user) {
        return userDao.add(user) == 1;
    }

    public Boolean delete(Integer id) {
        return userDao.delete(id) == 1;
    }


    public Boolean update(User user) {
        return userDao.update(user) == 1;
    }


    public Page<User> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Page<User> all = (Page<User>) userDao.findAll();
        return all;
    }

    public User findById(Integer id) {
        return userDao.findById(id);
    }
}
