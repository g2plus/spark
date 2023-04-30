package cn.fiesacyum.util;

import cn.fiesacyum.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringUtil {
    //此文件不在使用，可以删除
    private static ApplicationContext app;

    static {
        app = new AnnotationConfigApplicationContext(SpringConfig.class);
    }

    public static Object getBean(Class clazz){
        return app.getBean(clazz);
    }
}
