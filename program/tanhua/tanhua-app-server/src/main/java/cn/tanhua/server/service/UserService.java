package cn.tanhua.server.service;

import cn.tanhua.autoconfig.properties.HuanXinProperties;
import cn.tanhua.autoconfig.template.HuanXinTemplate;
import cn.tanhua.autoconfig.template.SmsTemplate;
import cn.tanhua.commons.utils.Constants;
import cn.tanhua.commons.utils.JWTUtils;
import cn.tanhua.dubbo.api.UserApi;
import cn.tanhua.model.pojo.User;
import cn.tanhua.model.vo.ErrorResult;
import cn.tanhua.server.exception.BusinessException;
import cn.tanhua.server.interceptor.UserHolder;
import com.aliyuncs.utils.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @DubboReference
    private UserApi userApi;

    @Autowired
    private HuanXinTemplate huanXinTemplate;

    //用来存储验证码
    private String code;


    public void sendVerificationCode(String phone) {
        //1、随机生成6位数字
        //String code = RandomStringUtils.randomNumeric(6);
        code="123456";//模拟登录测试
        //smsTemplate.sendVerficationCode(phone, code);
        //2.将验证码装配到Redis内存型数据库中，（验证码有效时间的设置，redis可设置key的有效时间，过期无法获取到对key
        //的value为null）
        redisTemplate.opsForValue().set("VERIFICATION_CODE_" + phone, code, Duration.ofMinutes(5));
    }

    public Map login(String phone, String code) {

        Map retMap=null;

        if(checkVerificationCode(phone,code)){
            //验证通过，根据用户的手机号来查询是否为新用户，是新用户将手机号与验证码作为密码保存到tb_user
            User user = userApi.findByMobile(phone);
            boolean isNew = false;
            //如果为null，进行需要新建一个user对象用来设置用户信息的保存
            if(null==user){
                user=new User();
                user.setMobile(phone);
                user.setPassword(DigestUtils.md5Hex(code));
                Long userId = userApi.save(user);//用户id在保存到数据库时确定
                user.setId(userId);

                String hxUser = "hx"+user.getId();
                String hxPassword = Constants.INIT_PASSWORD;
                //注册环信用户
                Boolean flag=huanXinTemplate.createUser(hxUser,hxPassword);
                if(flag){
                    //注册成功,更新用户的hxUser与hxPassword信息
                    user.setHxUser(hxUser);
                    user.setHxPassword(hxPassword);
                    userApi.update(user);
                }
                isNew=true;
            }
            //6.token不能包含用户的敏感信息
            //将用户的phone与id存入token
            Map tokenMap = new HashMap();
            tokenMap.put("id",user.getId());
            tokenMap.put("mobile",phone);
            final String token = JWTUtils.getToken(tokenMap);

            //7.数据的再次封装，将isNew信息与token字符串，保存到map集合
            retMap = new HashMap(16);
            retMap.put("token",token);
            retMap.put("isNew",isNew);
        }
        return retMap;

    }

    public boolean checkVerificationCode(String phone, String code) {

        String verificationCode = code;
        String codeInRedis = redisTemplate.opsForValue().get("VERIFICATION_CODE_" + phone);
        if (StringUtils.isEmpty(codeInRedis) || !codeInRedis.equals(verificationCode)) {
            //验证码无效
            //根据不同的响应设置创建不同的ErrorResult对象
            //抛出异常，即使处理（try...catch处理能够继续执行），
            //交给springmvc统一处理异常确保系统能够一直运行。
            //异常，程序中断，方法无返回值但系统仍可运行
            throw new BusinessException(ErrorResult.loginError());
            //结束
        }
        redisTemplate.delete("VERIFICATION_CODE_" + phone);
        return true;
    }

    public void updatePhoneNumber(String newPhoneNum) {
        User user = userApi.findByUserId(UserHolder.getUserId());
        user.setMobile(newPhoneNum);
        userApi.update(user);
    }
}
