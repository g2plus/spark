package cn.tanhua.dubbo.api;

import cn.tanhua.model.mongo.RecommendUser;
import cn.tanhua.model.vo.PageResult;
import cn.tanhua.model.vo.TodayBest;

import java.util.List;


public interface RecommendUserApi {

    RecommendUser getRecommendUser(Long userId);

    RecommendUser getRecommendUser();

    PageResult getRecommendUserList(Long toUserId, Integer page, Integer pagesize);

    RecommendUser doubleQuery(Long userIdViewed, Long userId);

    List<RecommendUser> card(Long userId);
}
