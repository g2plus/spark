package cn.tanhua.dubbo.api;

import cn.tanhua.model.mongo.Video;

import java.util.List;

public interface VideoApi {

    Boolean save(String picUrl, String videoUrl,Long userId);

    List<Video> findVideoByPids(List<Long> vid);

    List<Video> getVideoRandomly(int iPage, int pagesize);
}
