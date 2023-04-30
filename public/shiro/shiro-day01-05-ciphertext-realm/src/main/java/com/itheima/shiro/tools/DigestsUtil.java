package com.itheima.shiro.tools;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description：摘要
 */
public class DigestsUtil {

    public static final String SHA1 = "SHA-1";

    public static final Integer ITERATIONS = 512;

    /**
     * @param input 需要散列字符串
     * @param salt  盐字符串
     * @return
     * @Description sha1方法
     */
    public static String sha1(String input, String salt) {
        /*
        *   algorithmName – the MessageDigest algorithm name to use when performing the hash.
            source – the source object to be hashed.
            salt – the salt to use for the hash
            hashIterations – the number of times the source argument hashed for attack*/
        return new SimpleHash(SHA1, input, salt, ITERATIONS).toString();
    }

    /**
     * @return
     * @Description 随机获得salt字符串 随机盐生成策略
     */
    public static String generateSalt() {
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        return randomNumberGenerator.nextBytes().toHex();
    }


    /**
     * @param passwordPlainText 密码明文（用户输入的密码）
     * @return
     * @Description 生成密码字符密文和salt密文 模拟数据库拿出的盐值 与 加密后 的密码
     */
    public static Map<String, String> entryptPassword(String passwordPlainText) {
        Map<String, String> map = new HashMap<>();
        //随机获得salt字符串
        String salt = generateSalt();
        String password = sha1(passwordPlainText, salt);//用户输入的密码，使用随机盐加密生成的最终密码
        map.put("salt", salt);
        map.put("password", password);
        return map;
    }
}
