package cn.tanhua.server.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.tanhua.autoconfig.template.OssTemplate;
import cn.tanhua.commons.utils.Constants;
import cn.tanhua.dubbo.api.UserInfoApi;
import cn.tanhua.dubbo.api.VideoApi;
import cn.tanhua.model.mongo.Video;
import cn.tanhua.model.pojo.UserInfo;
import cn.tanhua.model.vo.ErrorResult;
import cn.tanhua.model.vo.PageResult;
import cn.tanhua.model.vo.VideoVo;
import cn.tanhua.server.exception.BusinessException;
import cn.tanhua.server.interceptor.UserHolder;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SmallVideosService {

    @Autowired
    private OssTemplate ossTemplate;

    /**
     *  用于文件上传或者下载
     */
    @Autowired
    private FastFileStorageClient client;

    @Autowired
    private FdfsWebServer webServer;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @DubboReference
    private UserInfoApi userInfoApi;

    @DubboReference
    private VideoApi videoApi;

    public void uploadVideo(MultipartFile videoThumbnail, MultipartFile videoFile) throws IOException {
        if(videoThumbnail.isEmpty()||videoFile.isEmpty()){
            //如果有一个文件为空,就抛出异常,提前结束
            throw new BusinessException(ErrorResult.error());
        }
        String picUrl = ossTemplate.upLoad(videoThumbnail.getInputStream(), videoThumbnail.getOriginalFilename());
        String fileName = videoFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        StorePath path = client.uploadFile(videoFile.getInputStream(),videoFile.getSize(),suffix,null);
        String videoUrl = webServer.getWebServerUrl()+path.getFullPath();
        Boolean save = videoApi.save(picUrl, videoUrl, UserHolder.getUserId());
        if (!save){
            throw new BusinessException(ErrorResult.error());
        }
    }

    //?springCache通用缓存,springEL表达式

    /**
     * videos:: 分组
     * @param page
     * @param pagesize
     * @return
     */
    @Cacheable(
            value="videos",
            key = "T(cn.tanhua.server.interceptor.UserHolder).getUserId()+'_'+#page+'_'+#pagesize")  //userid _ page_pagesize
    public PageResult loadVideo(int page, int pagesize) {
        //1.从redis中获取推荐视频
        String redisKey = Constants.VIDEOS_RECOMMEND +UserHolder.getUserId();
        String redisValue = redisTemplate.opsForValue().get(redisKey);
        List<Video> videoList = new ArrayList<>();
        int redisPages=0;
        if(!StringUtils.isEmpty(redisValue)){
            //在redis中获取vid,根据vid获取视频
            String[] vids = redisValue.split(",");
            int pages = vids.length % pagesize == 0 ? vids.length/pagesize:vids.length/pagesize+1;
            if(page<=pages) {
                List<Long> vid = Arrays.stream(vids)
                        .skip((page - 1) * pagesize)
                        .limit(pagesize)
                        .map(e -> Convert.toLong(e))
                        .collect(Collectors.toList());
                videoList = videoApi.findVideoByPids(vid);
            }
            redisPages = pages;
        }
        if(videoList.isEmpty()){
            //redis中数据读取完毕后开始从mongodb数据库中开始读取数据.因而需要减去redis存储数据的总页码数
            videoList = videoApi.getVideoRandomly(page-redisPages,pagesize);
        }
        //获取所有的用户id
        List<Long> userId = CollUtil.getFieldValues(videoList, "userId", Long.class);
        //获取所有用户的个人信息
        Map<Long, UserInfo> map = userInfoApi.findByIds(userId, null);
        List<VideoVo> list = new ArrayList<>();
        for (Video video : videoList) {
            //vo封装,添加数据至列表
            UserInfo userInfo = map.get(video.getUserId());
            if(userInfo!=null){
                VideoVo vo = VideoVo.init(userInfo, video);
                list.add(vo);
            }
        }
        //返回PageResult
        return new PageResult(page,pagesize,0L,list);
    }
}
