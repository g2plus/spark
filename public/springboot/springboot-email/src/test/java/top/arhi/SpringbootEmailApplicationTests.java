package top.arhi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.arhi.service.SendMailService;

@SpringBootTest
class SpringbootEmailApplicationTests {

    @Autowired
    private SendMailService sendMailService;

    @Test
    void contextLoads() {
        sendMailService.sendMail();
    }

}
