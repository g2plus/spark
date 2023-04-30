package com.itheima.shiro.client;

import com.itheima.shiro.tools.DigestsUtil;
import org.junit.Test;

import java.util.Map;

/**
 * @Description：测试
 */
public class ClientTest {


    @Test
    public void testDigestsUtil(){
       Map<String,String> map =  DigestsUtil.entryptPassword("123456");
       //key=value
       System.out.println("获得结果："+map);
    }

}
