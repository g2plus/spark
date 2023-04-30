package top.arhi.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.arhi.config.SpringConfig;
import top.arhi.domain.Account;
import top.arhi.service.AccountService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringConfig.class})
public class SqlTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testFindAll(){
        List<Account> all = accountService.findAll();
        all.stream().forEach(s-> System.out.println(s));
        for (Account account : all) {
            System.out.println(account.getUsername()+account.getPassword());
        }
    }

}
