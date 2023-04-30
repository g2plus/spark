package cn.tanhua.dubbo.util;

import cn.hutool.core.collection.CollUtil;
import cn.tanhua.model.mongo.Friend;
import cn.tanhua.model.mongo.Movement;
import cn.tanhua.model.mongo.MovementTimeLine;
import com.alibaba.nacos.api.config.filter.IFilterConfig;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeLineService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 开启线程池
     */
    @Async
    public void save(Movement movement) {
        //构造条件找到所有的朋友信息
        Criteria criteria = Criteria.where("userId").is(movement.getUserId());
        Query query = Query.query(criteria);
        List<Friend> friends = mongoTemplate.find(query, Friend.class);
        MovementTimeLine movementTimeLine = new MovementTimeLine();
        if(!CollUtil.isEmpty(friends)){
            for (Friend friend : friends) {
                if (friend!=null){
                    //设置timeline的信息
                    movementTimeLine.setId(ObjectId.get());
                    movementTimeLine.setMovementId(movement.getId());
                    movementTimeLine.setUserId(friend.getUserId());
                    movementTimeLine.setFriendId(friend.getFriendId());
                    movementTimeLine.setCreated(System.currentTimeMillis());
                    mongoTemplate.save(movementTimeLine);
                }
            }
        }
    }
}
