package com.itheima.shiro.tools;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description：摘要
 */
public class DigestsUtil {

    private static final String SHA1 = "SHA-1";

    private static final Integer ITERATIONS =2;


    /**
     * @Description 生成密码字符密文和salt密文
     * @param
     * @return
     */
    public static Map<String,String> entryptPassword(String passwordPlainText) {
       Map<String,String> map = new HashMap<>();
        //生成随机盐
       String salt = generateSalt();
       System.out.println("salt="+salt);
       String password =sha1(passwordPlainText,salt);
       System.out.println("password="+password);
       map.put("salt", salt);
       map.put("password", password);
       return map;
    }


    /**
     * @Description 随机获得salt字符串
     * @return
     */
    public static String generateSalt(){
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        //默认长度16
        return randomNumberGenerator.nextBytes().toHex();
    }


    /**
     * @Description sha1方法
     * @param input 需要散列字符串（hash算法，指定算法）
     * @param salt 盐字符串
     * @return
     */
    public static String sha1(String input, String salt) {
        //支持的算法类型见org.apache.shiro.crypto包
        return new SimpleHash(SHA1, input, salt,ITERATIONS).toString();
    }
}
