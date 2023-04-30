package cn.tanhua.server.service;

import cn.hutool.core.collection.CollUtil;
import cn.tanhua.autoconfig.template.HuanXinTemplate;
import cn.tanhua.commons.utils.Constants;
import cn.tanhua.dubbo.api.*;
import cn.tanhua.model.dto.RecommendUserDto;
import cn.tanhua.model.mongo.RecommendUser;
import cn.tanhua.model.mongo.UserLocation;
import cn.tanhua.model.pojo.Question;
import cn.tanhua.model.pojo.UserInfo;
import cn.tanhua.model.vo.ErrorResult;
import cn.tanhua.model.vo.NearUserVo;
import cn.tanhua.model.vo.PageResult;
import cn.tanhua.model.vo.TodayBest;
import cn.tanhua.server.exception.BusinessException;
import cn.tanhua.server.interceptor.UserHolder;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TanhuaService {

    @DubboReference
    private RecommendUserApi recommendUserApi;

    @DubboReference
    private UserInfoApi userInfoApi;

    @DubboReference
    private QuestionApi questionApi;

    @DubboReference
    private UserLikeApi userLikeApi;

    @DubboReference
    private UserLocationApi userLocationApi;

    @Autowired
    private HuanXinTemplate huanXinTemplate;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private MessagesService messagesService;

    public TodayBest getTodayBest(Long toUserId) {
        //根据app使用者userId查询推荐用户
        RecommendUser recommendUser = recommendUserApi.getRecommendUser(toUserId);
        if (recommendUser == null) {
            //获取得分超过90分的随机用户.
            recommendUser = recommendUserApi.getRecommendUser();
            //获取用户的ObjectId,如果为null，指定推荐1号用户
            if (recommendUser.getId() == null) {
                recommendUser.setUserId(1L);
                recommendUser.setScore(99D);
            }
        }
        //根据系统推荐的用户id查询Mysql获取用户信息
        UserInfo userInfo = userInfoApi.findById(recommendUser.getUserId());
        //进行初始化操作
        return TodayBest.init(recommendUser, userInfo);
    }


    public PageResult getRecommendation(RecommendUserDto recommendUserDto) {
        //调用recommendUserApi的getRecommendUserList方法
        PageResult pageResult = recommendUserApi
                .getRecommendUserList(UserHolder.getUserId(),
                        recommendUserDto.getPage(), recommendUserDto.getPagesize());
        //获取PageResult对像的list列表
        List<RecommendUser> items = (List<RecommendUser>) pageResult.getItems();
        //判断items的长度是否为0，items为null不可能进行数据的存储
        if (items.size() == 0) {
            return pageResult;
        }
        //使用hutools工具包
        List<Long> ids = CollUtil.getFieldValues(items, "userId", Long.class);
        //调用userInfoApi的findByIds方法,返会map结合，避免双层for循环的嵌套。
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(recommendUserDto.getAge());
        userInfo.setGender(recommendUserDto.getGender());
        Map<Long, UserInfo> map = userInfoApi.findByIds(ids, userInfo);

        List<TodayBest> list = new ArrayList<>();
        //使用item来遍历，不用id的原因L:item作为参数实现数据赋值到推荐用户信息中
        for (RecommendUser item : items) {
            userInfo = map.get(item.getUserId());
            //判断userInfo是否为null(copyProperties要求source，target都不能为null)
            //在mogodb中查到的所有id，根据id在mysql数据表查询，完成条件筛选，item的id，map结合可能不存在，
            //因此进行userInfo!=null,避免程序错误
            if (userInfo != null) {
                TodayBest vo = TodayBest.init(item, userInfo);
                list.add(vo);
            }
        }
        long counts = items.size();
        pageResult.setCounts(counts);
        //刷新数据，list集合存储真实信息。
        pageResult.setItems(list);
        return pageResult;
    }


    public TodayBest showPersonalInfo(Long userIdViewed) {
        //根据userIdViewed查找用户信息
        UserInfo userInfo = userInfoApi.findById(userIdViewed);
        //在Recommend_user中进行双向查询
        RecommendUser recommendUser = recommendUserApi.doubleQuery(userIdViewed, UserHolder.getUserId());
        return TodayBest.init(recommendUser, userInfo);
    }


    public void replyQuestions(Long userId, String reply) {
        /*
         * {"userId":106,"huanXinId":"hx106","nickname":"黑马小妹",
         * "strangerQuestion":"你喜欢去看蔚蓝的大海还是去爬巍峨的高山？",
         * "reply":"我喜欢秋天的落叶，夏天的泉水，冬天的雪地，只要有你一切皆可~"}
         * */
        Long myUserId = UserHolder.getUserId();
        String huanXinUserId = Constants.HX_USER_PREFIX + myUserId;
        String nickname = userInfoApi.findById(myUserId).getNickname();
        Question question = questionApi.findByUserId(userId);
        String strangerQuestion = question == null ? "How you doing?" : question.getTxt();
        Map retMap = new HashMap();
        retMap.put("userId", myUserId);
        retMap.put("huanXinId", huanXinUserId);
        retMap.put("nickname", nickname);
        retMap.put("strangerQuestion", strangerQuestion);
        retMap.put("reply", reply);
        JSONObject jsonObject = new JSONObject(retMap);
        Boolean flag = huanXinTemplate.sendMsg(Constants.HX_USER_PREFIX + userId, jsonObject.toString());
        if (!flag) {
            throw new BusinessException(ErrorResult.error());
        }
    }

    public String getQuestion(Long userId) {
        //根据userId查询问题
        Question question = questionApi.findByUserId(userId);
        String txt = question == null ? "How you doing?" : question.getTxt();
        if (question == null) {
            //如果question为null,保存默认问题值数据库
            Question questionDefault = new Question();
            question.setTxt("How you doing?");
            question.setUserId(userId);
            questionApi.saveQuestion(question);
        }
        return txt;
    }

    public List card(Long userId) {
        //获取排除用户喜欢与不喜欢的用户推荐列表
        List<RecommendUser> card = recommendUserApi.card(userId);
        if(CollUtil.isEmpty(card)){
            return null;
        }
        //根据推荐用户的字段userId获取到所有的userId
        List<Long> userIds = CollUtil.getFieldValues(card, "userId", Long.class);
        //获取userId与userInfo对应的map表
        Map<Long, UserInfo> map = userInfoApi.findByIds(userIds, null);
        List<TodayBest> list = new ArrayList<>();
        //遍历,构造封装
        for (RecommendUser recommendUser : card) {
            if(recommendUser!=null){
                UserInfo userInfo = map.get(recommendUser.getUserId());
                if (userInfo!=null){
                    TodayBest vo = TodayBest.init(recommendUser, userInfo);
                    list.add(vo);
                }
            }
        }
        //返回
        return list;

    }

    public void love(Long userId, Long likeUserId) {
        //方案一:saveOrUpdate一起处理.
        //方案二:save与update分开处理.
       /*TODO 方案二
        * Boolean flag = userLikeApi.recordExisted(userId,loveId);
        if(flag){
        }else{
        }*/
       //进行数据的更新或者保存
       Boolean flag = userLikeApi.saveOrUpdate(userId,likeUserId,true);
       if(!flag){
           //如果保存失败提前结束终止向下执行添加好友的过程
           throw new BusinessException(ErrorResult.error());
       }
       //将喜欢的人的id以set的形式存储在redis数据库中
        //1.移除可能存放在不喜欢集合中推荐用户id
        redisTemplate.opsForSet().remove(Constants.USER_NOT_LIKE_KEY + userId,likeUserId.toString());
       //2.添加发喜欢的用户到set集合
       redisTemplate.opsForSet().add(Constants.USER_LIKE_KEY + userId,likeUserId.toString());

       //双方互相喜欢添加好友
        addFriends(userId,likeUserId);
    }

    public void addFriends(Long userId,Long likeUserId){
        Boolean flag = redisTemplate.opsForSet().isMember(Constants.USER_LIKE_KEY + likeUserId, userId.toString());
        if(flag){
              messagesService.addFriends(userId,likeUserId);
        }
    }


    public void unlove(Long userId, Long likeUserId) {
        Boolean flag = userLikeApi.saveOrUpdate(userId,likeUserId,false);
        if(!flag){
            throw new BusinessException(ErrorResult.error());
        }
        redisTemplate.opsForSet().add(Constants.USER_NOT_LIKE_KEY + userId,likeUserId.toString());
        redisTemplate.opsForSet().remove(Constants.USER_LIKE_KEY + userId,likeUserId.toString());
        //无需进行删除好友关系操作,好友关系不存在
    }

    public List<NearUserVo> getUserNear(String gender, Long distance, Long userId) {
        //获取所有的用户地点信息列表
        List<Long> ids = userLocationApi.getUserNear(gender,Double.valueOf(distance.toString()),userId);
        //如果为空或为null,直接返回
        if(CollUtil.isEmpty(ids)){
            return null;
        }
        UserInfo userInfoFilter = new UserInfo();
        userInfoFilter.setGender(gender);
        Map<Long, UserInfo> map = userInfoApi.findByIds(ids, userInfoFilter);
        List<NearUserVo> list = new ArrayList<>();
        NearUserVo nearUserVo = null;
        for (Long id :ids) {
            if(id.equals(userId)){
                //排除个人信息
                continue;
            } else {
                UserInfo userInfo = map.get(id);
                if(userInfo!=null){
                    nearUserVo =NearUserVo.init(userInfo);
                    list.add(nearUserVo);
                }
            }
        }
        return list;
    }
}
