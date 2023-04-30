package cn.tanhua.server.service;

import cn.hutool.core.collection.CollUtil;
import cn.tanhua.autoconfig.template.HuanXinTemplate;
import cn.tanhua.commons.utils.Constants;
import cn.tanhua.dubbo.api.FriendApi;
import cn.tanhua.dubbo.api.UserInfoApi;
import cn.tanhua.model.mongo.Friend;
import cn.tanhua.model.pojo.UserInfo;
import cn.tanhua.model.vo.ContactVo;
import cn.tanhua.model.vo.ErrorResult;
import cn.tanhua.model.vo.PageResult;
import cn.tanhua.model.vo.UserInfoVo;
import cn.tanhua.server.exception.BusinessException;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MessagesService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private HuanXinTemplate huanXinTemplate;

    @DubboReference
    private FriendApi friendApi;

    @DubboReference
    private UserInfoApi userInfoApi;

    public UserInfoVo findByHuanXinId(String huanxinId) {
        //根据环信的id规则进行切割获取到userId
        return userInfoService.getUserInfoVo(Long.valueOf(huanxinId.substring(2)));
    }

    public void addFriends(Long userIdRequested, Long userIdResponsed) {
        Boolean flag = huanXinTemplate.addContact(Constants.HX_USER_PREFIX + userIdRequested,Constants.HX_USER_PREFIX + userIdResponsed);
        if(flag){
            try {
                friendApi.addFriends(userIdRequested,userIdResponsed);
            } catch (Exception e) {
                e.printStackTrace();
                huanXinTemplate.deleteContact(Constants.HX_USER_PREFIX + userIdRequested,Constants.HX_USER_PREFIX + userIdResponsed);
            }
        }else{
            throw new BusinessException(ErrorResult.error());
        }
    }

    public PageResult getContacts(Long userId, int page, int pagesize, String keyword) {
        //根据id查询friend数据表
        List<Friend> friends = friendApi.findByUserId(userId, page, pagesize);
        if (CollUtil.isEmpty(friends)){
            return new PageResult();
        }
        //根据friendId查询UserInfo信息表
        List<Long> friendIds = CollUtil.getFieldValues(friends, "friendId", Long.class);
        Map<Long, UserInfo> map = userInfoApi.findByIds(friendIds, null);
        List<ContactVo> list = new ArrayList<>();
        ContactVo contactVo = null;
        //循环遍历进行vo封装
        for (Long friendId : friendIds) {
            UserInfo userInfo = map.get(friendId);
            if(userInfo!=null){
                contactVo = ContactVo.init(userInfo);
                list.add(contactVo);
            }
        }
        //返回结果集
        return new PageResult(page,pagesize,0L,list);
    }
}
