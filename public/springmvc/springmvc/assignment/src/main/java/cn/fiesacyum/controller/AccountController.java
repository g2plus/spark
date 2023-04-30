package cn.fiesacyum.controller;

import cn.fiesacyum.pojo.Account;
import cn.fiesacyum.service.AccountService;
import cn.fiesacyum.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;
    @RequestMapping("/login")
    public String login(Account account, HttpServletRequest request){
      boolean access=accountService.verify(account);
      if(access){
          return "/ajax.jsp";
      }
      return "/login.jsp";
    }
    @RequestMapping("/list")
    @ResponseBody
    //基于jackon技术，使用@ResponseBody注解可以将返回的POJO对象转成json格式数据
    public List<Account> list(HttpServletRequest request, Model model){
        List<Account> all =  accountService.findAll();
        System.out.println(all);
        return all;
    }

    @RequestMapping("/save")
    public String save(Account account){
        accountService.save(account);
        return "/ajax.jsp";
    }

    @RequestMapping("/delete")
    public String delete(Account account){
        accountService.delete(account);
        return "/ajax.jsp";
    }

    @RequestMapping("/update")
    public String update(Account account){
        accountService.update(account);
        return "/ajax.jsp";
    }
}
