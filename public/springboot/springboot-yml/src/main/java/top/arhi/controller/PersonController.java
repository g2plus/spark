package top.arhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.arhi.pojo.Person;

/**
 * spring默认单例模式，如果存在成员变量,成员变量是被共享的 需要确保线程安全
 * 单例内有多例，内部对象为单例
 */
@RestController
@Scope("prototype")
public class PersonController {

    private int i = 0;

    @Autowired
    private Person person;

    @RequestMapping("/test")
    public synchronized String test() {

        System.out.println(i++);
        System.out.println(this);
        System.out.println(person);
        String[] address = person.getAddress();
        for (String s : address) {
            System.out.println(s);
        }
        return "test";
    }
}
