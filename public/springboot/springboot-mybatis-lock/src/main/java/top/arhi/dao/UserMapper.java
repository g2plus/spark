package top.arhi.dao;

import top.arhi.domain.User;

import java.util.List;

//@Mapper
public interface UserMapper {

    int save(User user);

    int delete(Integer id);


    int update(User user);


    List<User> findAll();


    User findById(Integer id);

}
