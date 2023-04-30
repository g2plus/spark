package com.heima.user;

import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Scanner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ApUserTest {

    @Autowired
    private ApUserMapper apUserMapper;
    @Test
    public void testFindByPhone(){
        System.out.print("请输入手机号:");
        String phone=new Scanner(System.in).next();
        ApUser apUser = apUserMapper.findByPhone("13511223456");
        System.out.println(apUser);
    }
}
