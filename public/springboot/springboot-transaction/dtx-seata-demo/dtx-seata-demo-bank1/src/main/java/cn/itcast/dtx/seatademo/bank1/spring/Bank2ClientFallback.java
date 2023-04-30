package cn.itcast.dtx.seatademo.bank1.spring;

import org.springframework.stereotype.Component;


//调用李四微服务的降级方法
@Component
public class Bank2ClientFallback implements Bank2Client {
    @Override
    public String transfer(Double amount) {
        return "failed";
    }
}
