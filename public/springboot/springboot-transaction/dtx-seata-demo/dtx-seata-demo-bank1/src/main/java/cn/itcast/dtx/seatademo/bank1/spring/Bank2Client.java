package cn.itcast.dtx.seatademo.bank1.spring;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//端口号，注册中心有信息
@FeignClient(value="seata-demo-bank2",fallback=Bank2ClientFallback.class)
public interface Bank2Client {

    //李四微服务实现接口
    @GetMapping("/bank2/transfer")
    String transfer(@RequestParam("amount") Double amount);

}
