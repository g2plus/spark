package cn.tanhua.dubbo.api;


import cn.tanhua.model.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoApi {

    Long save(UserInfo userInfo);

    /**
     * 根据token中的userId查找用户信息
     * @param userId
     * @return
     */
    UserInfo findById(Long userId);

    void update(UserInfo userInfo);

    Map<Long,UserInfo> findByIds(List<Long> ids, UserInfo userInfo);
}
