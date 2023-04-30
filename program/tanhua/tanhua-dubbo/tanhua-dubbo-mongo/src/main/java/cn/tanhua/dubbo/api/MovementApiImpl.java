package cn.tanhua.dubbo.api;

import cn.hutool.core.collection.CollUtil;
import cn.tanhua.dubbo.util.IdWorker;
import cn.tanhua.dubbo.util.TimeLineService;
import cn.tanhua.model.mongo.Movement;
import cn.tanhua.model.mongo.MovementTimeLine;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.ArrayList;
import java.util.List;

@DubboService
public class MovementApiImpl implements MovementApi{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private TimeLineService timeLineService;

    @Override
    public void publish(Movement movement) {
        movement.setPid(idWorker.getNextId("movement"));
        mongoTemplate.save(movement);
        timeLineService.save(movement);

    }

    @Override
    public List<Movement> getMyMovements(Long userId, int page, int pagesize) {
        //创建Criteria对象
        Criteria criteria = Criteria.where("userId").is(userId);
        //创建Query对象
        Query query = Query.query(criteria).with(Sort.by(Sort.Order.desc("created")))
                .skip((page - 1) * pagesize)
                .limit(pagesize);
        List<Movement> list = mongoTemplate.find(query, Movement.class);
        return list;
    }

    @Override
    public List<Movement> getFriendMoment(Long userId, int page, int pagesize) {

        Query query = Query.query(Criteria.where("friendId").is(userId)).with(Sort.by(Sort.Order.desc("created")))
                .skip((page - 1) * pagesize)
                .limit(pagesize);
        //根据条件寻找所有朋友的动态时间线信息
        List<MovementTimeLine> movementTimeLines = mongoTemplate.find(query, MovementTimeLine.class);
        //根据字段movementId获取取到movementId
        List<ObjectId> movementIds = CollUtil.getFieldValues(movementTimeLines, "movementId", ObjectId.class);

        if (CollUtil.isEmpty(movementIds)) {
            return null;
        }

        //创建movementList列表
        List<Movement> movementList = new ArrayList();

        //根据moventId查找动态表记录，將记录添加到list
        for (ObjectId movementId : movementIds) {
            Query queryMovement = Query.query(Criteria.where("id").is(movementId));
            Movement one = mongoTemplate.findOne(queryMovement, Movement.class);
            movementList.add(one);
        }

        //返回结果
        return movementList;
    }

    @Override
    public List<Movement> getMovementsRandomly(int pagesize) {
        TypedAggregation aggregation = Aggregation.newAggregation(Movement.class, Aggregation.sample(pagesize));
        AggregationResults<Movement> movements = mongoTemplate.aggregate(aggregation, Movement.class);
        List<Movement> mappedResults = movements.getMappedResults();
        return mappedResults;
    }



    @Override
    public List<Movement> findMovementByPids(List<Long> pids) {
        Query query = Query.query(Criteria.where("pid").in(pids));
        List<Movement> movementList = mongoTemplate.find(query, Movement.class);
        return movementList;
    }

    @Override
    public Movement findByMovementId(String movementId) {
        Query query = Query.query(Criteria.where("id").is(new ObjectId(movementId)));
        Movement one = mongoTemplate.findOne(query, Movement.class);
        return one;
    }
}
