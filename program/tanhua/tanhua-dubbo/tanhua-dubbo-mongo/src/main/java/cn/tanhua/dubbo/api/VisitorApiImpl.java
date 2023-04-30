package cn.tanhua.dubbo.api;

import cn.tanhua.model.mongo.Visitors;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@DubboService
public class VisitorApiImpl implements VisitorsApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RecommendUserApi recommendUserApi;

    /**
     * 一天同一用户访问同一用户的记录只保存一条记录,
     * 如果记录存在的情况下,仅对date字段进行更新,为访客记录提供参考时间
     *
     * @param userId
     * @param userIdViewed
     */
    @Override
    public void addView(Long userId, Long userIdViewed) {
        //.判读数据库是否有此记录,有更新,无保存
        Criteria criteria = Criteria.where("userId").is(userIdViewed)
                .and("visitorUserId").is(userId)
                .and("visitDate").is(new SimpleDateFormat("yyyyMMdd").format(new Date()))
                .and("from").is("首页");
        Query query = Query.query(criteria);
        Visitors one = mongoTemplate.findOne(query, Visitors.class);
        if (one == null) {
            if (!userId.equals(userIdViewed)) {
                one = new Visitors();
                one.setId(ObjectId.get());
                one.setDate(System.currentTimeMillis());
                one.setUserId(userIdViewed);
                one.setScore(recommendUserApi.doubleQuery(userId, userIdViewed).getScore());
                one.setVisitorUserId(userId);
                one.setFrom("首页");
                one.setVisitDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
                mongoTemplate.save(one);
            }
        } else {
            Update update = new Update();
            update.set("date", System.currentTimeMillis());
            update.set("from", "首页");
            mongoTemplate.findAndModify(query, update, Visitors.class);
        }
    }

    @Override
    public List<Visitors> recentVisitors(Long date, Long userId) {
        List<Visitors> visitors = new ArrayList<>();
        if (date == null) {
            Query query = Query.query(Criteria.where("userId").is(userId))
                    .with(Sort.by(Sort.Order.desc("date")))
                    .skip(0)
                    .limit(5);
            visitors = mongoTemplate.find(query, Visitors.class);
        } else {
            Query query = Query.query(Criteria.where("userId").is(userId).and("date").gt(date))
                    .with(Sort.by(Sort.Order.desc("date")));
            visitors = mongoTemplate.find(query, Visitors.class);
        }
        return visitors;
    }
}
