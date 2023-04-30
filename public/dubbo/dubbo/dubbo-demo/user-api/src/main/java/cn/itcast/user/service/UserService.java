package cn.itcast.user.service;


import cn.itcast.user.domain.User;

public interface UserService {

    String queryUsername(Long id);

    User queryById(Long id);
}
