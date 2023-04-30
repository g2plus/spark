package cn.tanhua.dubbo.api;

import cn.tanhua.model.mongo.Friend;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@DubboService
public class FriendApiImpl implements FriendApi {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Friend> findByUserId(Long userId, Integer page, Integer pagesize) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Friend.class);
    }

    @Override
    public void addFriends(Long userIdRequested, Long userIdResponsed) {
        //查询数据库,判断好友关系是否已经存在
        Criteria criteria1 = Criteria.where("userId").is(userIdRequested).and("friendId").is(userIdResponsed);
        Query query1 = Query.query(criteria1);
        Friend one = mongoTemplate.findOne(query1, Friend.class);
        //如果好友关系不存在`
        if(one==null){
            //建立好友关系保存至数据库
            one = new Friend();
            one.setUserId(userIdRequested);
            one.setFriendId(userIdResponsed);
            one.setCreated(System.currentTimeMillis());
            mongoTemplate.save(one);
        }
        //查询数据库,判断好友关系是否已经存在
        Criteria criteria2 = Criteria.where("userId").is(userIdResponsed).and("friendId").is(userIdRequested);
        Query query2 = Query.query(criteria2);
        Friend two = mongoTemplate.findOne(query2,Friend.class);
        //如果好友关系不存在`
        if(two==null){
            //建立好友关系保存至数据库
            two = new Friend();
            two.setUserId(userIdResponsed);
            two.setFriendId(userIdRequested);
            two.setCreated(System.currentTimeMillis());
            mongoTemplate.save(two);
        }
    }
}
