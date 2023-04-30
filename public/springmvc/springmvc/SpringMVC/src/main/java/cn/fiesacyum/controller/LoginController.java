package cn.fiesacyum.controller;

import cn.fiesacyum.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/login")
    public String login(Account account, HttpServletRequest request){
        if("root".equals(account.getName())&&"root".equals(account.getPassword())){
            request.setAttribute("account",account);
            return "welcome";
        }else{
            return "failed";
        }
    }
}
