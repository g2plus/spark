package top.arhi;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import top.arhi.service.UserService;
import top.arhi.service.impl.UserServiceImpl;
import top.arhi.service.proxy.CglibProxy;
import top.arhi.service.proxy.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Application {



    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.action();

        UserService userService1 = JDKProxy.createUserService(userService);
        userService1.action();


        UserService userService2 = CglibProxy.createUserService(userService.getClass());
        userService2.action();


        Class[] interfaces = {UserService.class};
        UserService proxy = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object ret =method.invoke(userService,args);
                if("action".equals(method.getName())){
                    System.out.println("睡觉");
                    System.out.println("打豆豆");
                }
                return ret;
            }
        });
        proxy.action();

    }
}
