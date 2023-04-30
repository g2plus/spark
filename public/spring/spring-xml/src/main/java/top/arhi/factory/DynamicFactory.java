package top.arhi.factory;

import top.arhi.service.UserService;
import top.arhi.service.impl.UserServiceImpl;

public class DynamicFactory {
    public UserService getUserService2(){
        return new UserServiceImpl();
    }
}
