package top.arhi.mapper;

import top.arhi.pojo.User;

import java.util.List;

public interface UserMapper {

    int save(User user);

    int delete(Integer id);

    int update(User user);

    List<User> findAll();

    User findById(Integer id);
}
