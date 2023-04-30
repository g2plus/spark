package cn.tanhua.dubbo.api;

import cn.tanhua.model.pojo.User;

import java.util.List;
import java.util.Map;


public interface UserApi {

    User findByMobile(String phone);

    /**
     * @param user
     * @return 返回用户的id，用于构建token字符串
     */
    Long save(User user);


    void update(User user);


    //根据用户查找用户信息。更换手机号时，无法从token中获取旧手机号
    User findByUserId(Long userId);

    List<User> findAllUser();
}
