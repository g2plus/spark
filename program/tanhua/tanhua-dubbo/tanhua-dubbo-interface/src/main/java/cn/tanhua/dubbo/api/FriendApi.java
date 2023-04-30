package cn.tanhua.dubbo.api;

import cn.tanhua.model.mongo.Friend;

import java.util.List;

public interface FriendApi {

    List<Friend> findByUserId(Long userId, Integer page, Integer pagesize);

    void addFriends(Long userIdRequested, Long userIdResponsed);
}
