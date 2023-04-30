package cn.itcast.dtx.seatademo.bank2.controller;

import cn.itcast.dtx.seatademo.bank2.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bank2Controller {

    @Autowired
    AccountInfoService accountInfoService;

    //为什么不添加/bank2，应用上下文的路径为/bank2
    @GetMapping("/transfer")
    public String transfer(Double amount) {
        accountInfoService.updateAccountBalance("2", amount);
        return "bank2 + " + amount;
    }
}