package cn.itcast.dubbo.api;


import cn.itcast.dubbo.domain.User;

public interface UserService {

    User queryById(Long id);
}
