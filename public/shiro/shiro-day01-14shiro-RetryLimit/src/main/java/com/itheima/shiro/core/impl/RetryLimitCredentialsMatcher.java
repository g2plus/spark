package com.itheima.shiro.core.impl;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @Description：自定义密码比较器
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    private RedissonClient redissonClient;

    private static Long RETRY_LIMIT_NUM = 2L;

    public RetryLimitCredentialsMatcher(String hashAlgorithmName,RedissonClient redissonClient) {
        super(hashAlgorithmName);
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String loginName = (String) token.getPrincipal();
        // 1、获取系统中是否已有登录次数缓存,缓存对象结构预期为："用户名--登录次数"。
        //利用原子类的可见性，避免登录次数不进行统计。
        RAtomicLong atomicLong = redissonClient.getAtomicLong(loginName);
        //2、如果之前没有登录缓存，则创建一个登录次数缓存。
        //初次登录获取的值为0
        //多线线程，每个线程具备独立的运行空间，
        //如果多个线程操作操作同一份数据成员数据，线程之间不进行通信，存在安全问题，通过加锁实现通信，获取使用等待与唤醒机制实现通信
        //方法中切忌使用+进行字符串的拼接，这样浪费线程的运行内存。应该使用StringBuilder。
        //一般在类数据的位置，定义的对象为读操作不会操作安全问题。
        long retryFlat = atomicLong.get();
        //判断是否超过次数
        if (retryFlat>RETRY_LIMIT_NUM){
            //3、如果缓存次数已经超过限制，则驳回本次登录请求。
            //Set a timeout for object. After the timeout has expired, the key will automatically be deleted.
            atomicLong.expire(10, TimeUnit.MINUTES);
            //在后端显示，没有显示给用户，模拟效果。
            throw new ExcessiveAttemptsException("密码次数错误3次，请10分钟后重试");
        }
        //4、将缓存记录的登录次数加1,设置指定时间内有效
        atomicLong.incrementAndGet();


        atomicLong.expire(10, TimeUnit.MINUTES);

        //5、验证用户本次输入的帐号密码，如果登录登录成功，则清除掉登录次数的缓存
        boolean flag = super.doCredentialsMatch(token, info);
        if (flag){
            atomicLong.delete();
        }
        return flag;
    }
}
