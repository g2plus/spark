package com.itheima.shiro.client;

import com.itheima.shiro.tools.EncodesUtil;
import org.junit.Test;

/**
 * @Description：测试
 */
public class ClientTest {

    /**
     * @Description 测试16进制编码
     */
    @Test
    public void testHex(){
        String val = "12";
        //encrypt code
        String flag = EncodesUtil.encodeHex(val.getBytes());
        System.out.println(flag);
        //dencrypt code
        String valHandler1 = new String(EncodesUtil.decodeHex(flag)); //创建一个新的String对象放到堆上，常量池中存在不创建，无创建
        String valHandler2 = "12";
        System.out.println("比较结果："+val.equals(valHandler1));//true;
        System.out.println(val==valHandler1);//false
        System.out.println(val==valHandler2);//true

    }

    /**
     * @Description 测试base64编码
     */
    @Test
    public void testBase64(){
        String val = "12";
        String flag = EncodesUtil.encodeBase64(val.getBytes());
        String valHandler = new String(EncodesUtil.decodeBase64(flag));
        System.out.println("比较结果："+val.equals(valHandler));
    }


}
