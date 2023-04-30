package top.arhi.service.impl;

import top.arhi.service.UserService;

public class UserServiceImpl implements UserService {

    public void init(){
        System.out.println("init");
    }

    public void destroy(){
        System.out.println("destroy");
    }
    public void addUser() {
        System.out.println("添加用户成功!");
    }
}
