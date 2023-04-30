package cn.fiesacyum.service.impl;

import cn.fiesacyum.dao.UserDao;
import cn.fiesacyum.domain.User;
import cn.fiesacyum.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;


    public void save(User user) {
        userDao.save(user);
    }

    public void delete(String uuid) {
        userDao.delete(uuid);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public PageInfo<User> findAll(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> all = userDao.findAll();
        return new PageInfo<>(all);

    }

    public User get(String uuid) {
       return userDao.get(uuid);
    }

    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }
}
