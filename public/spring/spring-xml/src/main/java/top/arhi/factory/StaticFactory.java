package top.arhi.factory;

import top.arhi.service.UserService;
import top.arhi.service.impl.UserServiceImpl;

public class StaticFactory {
    public static UserService getUserService(){
        return new UserServiceImpl();
    }
}
