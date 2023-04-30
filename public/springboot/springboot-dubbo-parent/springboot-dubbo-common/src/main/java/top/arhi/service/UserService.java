package top.arhi.service;

import top.arhi.domain.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

    boolean save(User user);

    boolean delete(Integer id);

    boolean update(User user);

    PageInfo<User> findAll(Integer page, Integer size);

    User findById(Integer id);
}