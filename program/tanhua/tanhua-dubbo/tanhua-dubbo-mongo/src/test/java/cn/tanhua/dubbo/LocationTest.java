package cn.tanhua.dubbo;

import cn.tanhua.model.mongo.UserLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LocationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void getLocation(){
        Query query = Query.query(Criteria.where("userId").is(106L));
        UserLocation location = mongoTemplate.findOne(query, UserLocation.class);
        //location记录是否存在
        if(location==null){
            System.out.println("无记录");
        }
        //获取圆点
        GeoJsonPoint centre = location.getLocation();
        //确认半径
        Distance radius = new Distance(3000 / 1000.0, Metrics.KILOMETERS);
        //确认覆盖范围
        Circle circle = new Circle(centre,radius);
        //确认除了原点之外的所有点
        Query inside = Query.query(Criteria.where("location").withinSphere(circle));
        List<UserLocation> userLocations = mongoTemplate.find(inside, UserLocation.class);
        for (UserLocation userLocation : userLocations) {
            System.out.println(userLocation);
        }
    }

    @Test
    public void updateLocation(){
        Query query = Query.query(Criteria.where("userId").is(106L));
        Update update = new Update();
        update.set("location",new GeoJsonPoint(116.403694,39.9914937));
        mongoTemplate.findAndModify(query,update,UserLocation.class);
    }

    @Test
    public void testLocation(){
        //1、根据用户id，查询用户的位置信息
        Query query = Query.query(Criteria.where("userId").is(106L));
        UserLocation location = mongoTemplate.findOne(query, UserLocation.class);
        if(location == null) {
            System.out.println("no results");
        }
        //2、已当前用户位置绘制原点
        GeoJsonPoint point = location.getLocation();
        //3、绘制半径
        Distance distance = new Distance(300000.0 / 1000, Metrics.KILOMETERS);
        //4、绘制圆形
        Circle circle = new Circle(point, distance);
        //5、查询
        Query locationQuery = Query.query(Criteria.where("location").withinSphere(circle));
        List<UserLocation> list = mongoTemplate.find(locationQuery, UserLocation.class);
        for (UserLocation userLocation : list) {
            System.out.println(userLocation);
        }
    }

    //查询附近且获取间距
    @Test
    public void testNear1() {
        //1、根据用户id，查询用户的位置信息
        Query query = Query.query(Criteria.where("userId").is(106L));
        UserLocation location = mongoTemplate.findOne(query, UserLocation.class);
        if(location == null) {
            System.out.println("no results");
        }
        //2、已当前用户位置绘制原点
        GeoJsonPoint point = location.getLocation();
        //2、构建NearQuery对象
        NearQuery nearQuery = NearQuery.near(point, Metrics.KILOMETERS).maxDistance(1, Metrics.KILOMETERS);
        //3、调用mongoTemplate的geoNear方法查询
        GeoResults<UserLocation> results = mongoTemplate.geoNear(nearQuery, UserLocation.class);
        //4、解析GeoResult对象，获取距离和数据
        for (GeoResult<UserLocation> result : results) {
            System.out.println(result.getContent().getAddress());
            System.out.println(result.getDistance().getValue());
        }
    }




}
