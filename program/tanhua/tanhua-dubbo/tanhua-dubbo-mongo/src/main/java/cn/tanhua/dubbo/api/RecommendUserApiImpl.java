package cn.tanhua.dubbo.api;


import cn.hutool.core.collection.CollUtil;
import cn.tanhua.model.mongo.RecommendUser;
import cn.tanhua.model.mongo.UserLike;
import cn.tanhua.model.vo.PageResult;
import cn.tanhua.model.vo.TodayBest;
import org.apache.dubbo.config.annotation.DubboService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.awt.*;
import java.util.List;
import java.util.Random;

@DubboService
public class RecommendUserApiImpl implements RecommendUserApi {

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 推荐给登录用户，其中得分最高的用户
     */
    @Override
    public RecommendUser getRecommendUser(Long toUserId) {
        //创建Criteria对象
        Criteria criteria = Criteria.where("toUserId").is(toUserId);
        //创建Query对象
        Query query = Query.query(criteria)
                //根据分数倒序排列，最高得分推荐用户排列第一
                .with(Sort.by(Sort.Order.desc("score")))
                //可省略，默认跳过0个
                .skip(0)
                .limit(1);
        //使用mogoTemplate,并传入querry对象与指定接收数据的类型,并返回对象。
        return mongoTemplate.findOne(query, RecommendUser.class);
    }


    /**
     * 获取系统随机推荐用户
     */
    @Override
    public RecommendUser getRecommendUser() {
        //创建Criteria对象
        Criteria criteria = Criteria.where("score").gte(90D);
        //创建Query对像
        Query query = new Query(criteria);
        //查询分数在90分以上的用户信息
        List<RecommendUser> recommendUsers = mongoTemplate.find(query, RecommendUser.class);
        if(recommendUsers.size()!=0){
            //系统随意分配
            Random random = new Random();
            int index = random.nextInt(recommendUsers.size());
            return recommendUsers.get(index);
        }else{
            return new RecommendUser();
        }
    }

    @Override
    public PageResult getRecommendUserList(Long toUserId, Integer page, Integer pagesize) {
        //创建Criteria对象
        Criteria criteria = Criteria.where("toUserId").is(toUserId);
        //创建Query对象
        Query queryCount = new Query(criteria);
        //统计总记录数
        long count = mongoTemplate.count(queryCount, RecommendUser.class);
        //获取指定页码及条数的数据信息
        Query queryList = Query.query(criteria).with(Sort.by(Sort.Order.desc("score"))).
                skip((page-1)*pagesize).
                limit(pagesize);
        List<RecommendUser> recommendUsersList = mongoTemplate.find(queryList, RecommendUser.class);
        return new PageResult(page,pagesize,count,recommendUsersList);
    }

    @Override
    public RecommendUser doubleQuery(Long userIdViewed, Long userId) {
        //创建Criteria对象
        Criteria criteria = Criteria.where("userId").is(userIdViewed).and("toUserId").is(userId);
        //创建Query对象
        Query query = Query.query(criteria);
        //根据条件进行查询
        RecommendUser one = mongoTemplate.findOne(query, RecommendUser.class);
        if(one==null){
            //创建条件,获取被查看用户推荐给其他用户中缘分值最高的记录
            Criteria criteriaAgain=Criteria.where("userId").is(userIdViewed);
            Query queryAgain = Query.query(criteriaAgain).with(Sort.by(Sort.Order.desc("score"))).skip(0).limit(1);
            RecommendUser oneAgain = mongoTemplate.findOne(queryAgain, RecommendUser.class);
            return oneAgain;
        }
        return one;
    }

    @Override
    public List<RecommendUser> card(Long userId) {
        //查找userLike数据表,找到用户喜欢不喜欢的用户
        Query query = Query.query(Criteria.where("userId").is(userId));
        List<UserLike> userLikes = mongoTemplate.find(query, UserLike.class);
        if(CollUtil.isEmpty(userLikes)){
            Query recommendUserQuery = Query.query(Criteria.where("toUserId").is(userId));
            List<RecommendUser> recommendUsers = mongoTemplate.find(recommendUserQuery, RecommendUser.class);
            if(CollUtil.isEmpty(recommendUsers)){
                //从数据库中随机挑选10条数据(评分超过70分的用户)进行构建list
                Criteria randomCriteria = Criteria.where("score").gte(70D);
                TypedAggregation<RecommendUser> newAggregation = TypedAggregation.newAggregation(RecommendUser.class,
                        Aggregation.match(randomCriteria),
                        Aggregation.sample(10)
                );
                AggregationResults<RecommendUser> results = mongoTemplate.aggregate(newAggregation, RecommendUser.class);
                return results.getMappedResults();
            }else{
                //直接返回
                return recommendUsers;
            }
        }else{
            Query recommendUserQuery = Query.query(Criteria.where("toUserId").is(userId));
            List<RecommendUser> recommendUsers = mongoTemplate.find(recommendUserQuery, RecommendUser.class);
            List<Long> likeUserIds = CollUtil.getFieldValues(userLikes, "likeUserId", Long.class);
            if (CollUtil.isEmpty(recommendUsers)){
                Criteria randomCriteria = Criteria.where("score").gte(70D).and("userId").nin(likeUserIds);
                TypedAggregation<RecommendUser> newAggregation = TypedAggregation.newAggregation(RecommendUser.class,
                        Aggregation.match(randomCriteria),
                        Aggregation.sample(10)
                );
                AggregationResults<RecommendUser> results = mongoTemplate.aggregate(newAggregation, RecommendUser.class);
                return results.getMappedResults();
            }else{
                Query queryAgain = Query.query(Criteria.where("toUserId").is(userId).and("userId").nin(likeUserIds));
                return mongoTemplate.find(queryAgain, RecommendUser.class);
            }
        }
    }


}
