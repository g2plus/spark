package cn.tanhua.dubbo.api;

import cn.tanhua.model.mongo.UserLike;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


@DubboService
public class UserLikeApImpl implements UserLikeApi {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Boolean saveOrUpdate(Long userId, Long likeUserId,Boolean isLike) {
        try {
            //构建条件进行查询
            Criteria criteria = Criteria.where("userId").is(userId).and("likeUserId").is(likeUserId);
            Query query = Query.query(criteria);
            if(mongoTemplate.exists(query, UserLike.class)){
                //如果存在记录对记录进行更新
                // (严格意义上无需判读是否存在,因为卡片中的用户id不可能出现在UserLike数据表(显示列表时进行了过滤))
                Update update = new Update();
                update.set("isLike",isLike).set("updated",System.currentTimeMillis());
                mongoTemplate.findAndModify(query, update, UserLike.class);
            }else{
                //记录不存在,进行记录保存
                UserLike userLike = new UserLike();
                userLike.setId(ObjectId.get());
                userLike.setUserId(userId);
                userLike.setLikeUserId(likeUserId);
                Long time = System.currentTimeMillis();
                userLike.setCreated(time);
                userLike.setIsLike(isLike);
                userLike.setUpdated(time);
                mongoTemplate.save(userLike);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
