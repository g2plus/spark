package cn.tanhua.dubbo.api;

import cn.tanhua.model.mongo.Visitors;

import java.util.List;

public interface VisitorsApi {

    void addView(Long userId, Long userIdViewed);

    List<Visitors> recentVisitors(Long date,Long userId);
}
