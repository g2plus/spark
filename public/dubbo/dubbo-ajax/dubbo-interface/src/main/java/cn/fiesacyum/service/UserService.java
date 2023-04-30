package cn.fiesacyum.service;

import cn.fiesacyum.pojo.User;
import com.github.pagehelper.Page;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    public Boolean add(User user);


    public Boolean delete(Integer id);


    public Boolean update(User user);

    public Page<User> findAll(Integer page, Integer size);

    public User findById(Integer id);
}