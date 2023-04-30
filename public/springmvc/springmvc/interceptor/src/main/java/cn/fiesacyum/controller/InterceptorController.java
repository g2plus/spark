package cn.fiesacyum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InterceptorController {
    @RequestMapping("/handleRun")
    public String handleRun() {
        System.out.println("业务处理器运行------------main");
        return "page.jsp";
    }
}
