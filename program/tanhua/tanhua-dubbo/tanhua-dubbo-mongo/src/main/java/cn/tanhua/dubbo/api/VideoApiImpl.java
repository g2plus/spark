package cn.tanhua.dubbo.api;

import cn.tanhua.dubbo.util.IdWorker;
import cn.tanhua.model.mongo.Video;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@DubboService
public class VideoApiImpl implements VideoApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;

    @Override
    public Boolean save(String picUrl, String videoUrl, Long userId) {
        try {
            Query query = Query.query(Criteria.where("picUrl").is(picUrl).and("videoUrl").is(videoUrl).and("userId").is(userId));
            if(!mongoTemplate.exists(query, Video.class)){
                Video video = new Video();
                video.setId(ObjectId.get());
                video.setVid(idWorker.getNextId("vid"));
                video.setUserId(userId);
                video.setPicUrl(picUrl);
                video.setVideoUrl(videoUrl);
                video.setCreated(System.currentTimeMillis());
                video.setText("How you doing?");
                mongoTemplate.save(video);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Video> findVideoByPids(List<Long> vid) {
        Query query = Query.query(Criteria.where("vid").in(vid));
        List<Video> videos = mongoTemplate.find(query, Video.class);
        return videos;
    }

    @Override
    public List<Video> getVideoRandomly(int iPage, int pagesize) {
        //Criteria条件不存在时,相当于选中整个文档
        Query query = new Query().skip((iPage-1)*pagesize)
                .limit(pagesize)
                //获取最新的数据
                .with(Sort.by(Sort.Order.desc("created")));
        List<Video> videos = mongoTemplate.find(query, Video.class);
        return videos;
    }

}
