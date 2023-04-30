package top.arhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.arhi.domain.User;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "{\"msg\":\"Hello!\"}";
    }

    @RequestMapping("/user")
    @ResponseBody
    public User getUser(){
        return new User("何佳铭",24);
    }

}
