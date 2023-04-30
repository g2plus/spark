
package top.arhi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.arhi.config.SpringConfig2;
import top.arhi.service.AccoutService3;
import top.arhi.service.PasswordService3;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= SpringConfig2.class)
public class AopTest {

    @Autowired
    private AccoutService3 accountService;

    @Autowired
    private PasswordService3 passwordService;

    @Test
    public void testAdviceBefore(){
        System.out.println(accountService.getClass());
        accountService.add("root", "root");
    }

    @Test
    public void testPasswordTrim(){
        boolean flag = passwordService.openURL("http://www.baidu.com/", "root ");
        System.out.println(flag);
    }


}
