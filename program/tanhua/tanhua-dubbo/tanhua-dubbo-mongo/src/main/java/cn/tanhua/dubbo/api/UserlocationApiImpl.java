package cn.tanhua.dubbo.api;

import cn.hutool.core.collection.CollUtil;
import cn.tanhua.model.mongo.UserLocation;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


@DubboService
public class UserlocationApiImpl implements UserLocationApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Boolean updateLocation(Long userId, Double longitude, Double latitude, String address) {
        try {
            Query query = Query.query(Criteria.where("userId").is(userId));
            UserLocation userLocation= mongoTemplate.findOne(query, UserLocation.class);
            if (userLocation==null){
                //记录不存在进行数据的保存
                //创建UserLocation对象
                userLocation = new UserLocation();
                userLocation.setId(ObjectId.get());
                userLocation.setUserId(userId);
                GeoJsonPoint location = new GeoJsonPoint(longitude,latitude);
                userLocation.setLocation(location);
                userLocation.setAddress(address);
                Long time = System.currentTimeMillis();
                userLocation.setCreated(time);
                userLocation.setUpdated(time);
                userLocation.setLastUpdated(time);
                mongoTemplate.save(userLocation);
            }else{
                //记录存在,更新location,address,updated域
                Long lastUpdated = userLocation.getLastUpdated();
                Update update = new Update();
                update.set("location",new GeoJsonPoint(longitude,latitude));
                update.set("address",address);
                update.set("lastUpdated",lastUpdated);
                update.set("updated",System.currentTimeMillis());
                mongoTemplate.findAndModify(query,update,UserLocation.class);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Long> getUserNear(String gender, Double distance,Long userId) {
        //获取本人所在的用户地址
        Query query = Query.query(Criteria.where("userId").is(userId));
        UserLocation location = mongoTemplate.findOne(query, UserLocation.class);
        //location记录是否存在
        if(location==null){
            return null;
        }
        //获取圆点
        GeoJsonPoint  centre = location.getLocation();
        //确认半径
        Distance  radius = new Distance(distance / 1000, Metrics.KILOMETERS);
        //确认覆盖范围
        Circle circle = new Circle(centre,radius);
        //确认除了原点之外的所有点
        Query inside = Query.query(Criteria.where("location").withinSphere(circle));
        List<UserLocation> userLocations = mongoTemplate.find(inside, UserLocation.class);
        //Geo 不支持序列化,不能通过网络传递
        List<Long> userIds = CollUtil.getFieldValues(userLocations, "userId", Long.class);
        return userIds;
    }
}
