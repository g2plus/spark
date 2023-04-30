package cn.tanhua.server.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.tanhua.autoconfig.template.OssTemplate;
import cn.tanhua.commons.utils.Constants;
import cn.tanhua.dubbo.api.MovementApi;
import cn.tanhua.dubbo.api.UserInfoApi;
import cn.tanhua.dubbo.api.VisitorsApi;
import cn.tanhua.model.mongo.Movement;
import cn.tanhua.model.mongo.Visitors;
import cn.tanhua.model.pojo.UserInfo;
import cn.tanhua.model.vo.ErrorResult;
import cn.tanhua.model.vo.MovementVo;
import cn.tanhua.model.vo.PageResult;
import cn.tanhua.model.vo.VisitorsVo;
import cn.tanhua.server.exception.BusinessException;
import cn.tanhua.server.interceptor.UserHolder;
import com.aliyuncs.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.bson.types.ObjectId;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovementService {

    @DubboReference
    private MovementApi movementApi;

    @Autowired
    private OssTemplate ossTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @DubboReference
    private VisitorsApi visitorsApi;

    @DubboReference
    private UserInfoApi userInfoApi;


    public void publishMoment(MultipartFile[] imageContent, Movement movement) throws IOException {
        //1、判断发布动态的内容是否存在,不存在结束
        if (StringUtils.isEmpty(movement.getTextContent())) {
            throw new BusinessException(ErrorResult.contentError());
            //结束
        }
        //2.设置movement中id,userId，medias，created信息
        movement.setId(ObjectId.get());

        movement.setUserId(UserHolder.getUserId());

        List<String> medias = new ArrayList<>();
        if (imageContent.length != 0) {
            for (MultipartFile multipartFile : imageContent) {
                String url = ossTemplate.upLoad(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
                medias.add(url);
            }
            movement.setMedias(medias);
        } else {

        }
        movement.setCreated(System.currentTimeMillis());

        //3.调用movementApi进行发布（保存）
        movementApi.publish(movement);
    }


    public PageResult getMyMovements(Long userId, int page, int pagesize) {
        //根据userId获取到个人所有动态
        List<Movement> list = movementApi.getMyMovements(userId, page, pagesize);
        return getPageResult(page, pagesize, list);
    }

    public PageResult getFriendMoment(Long userId, int page, int pagesize) {
        //查询时间线表，以登录用户id当作movement_timeline的friendId进行查詢封裝所有的movement信息
        List<Movement> movements = movementApi.getFriendMoment(userId, page, pagesize);
        return getPageResult(page, pagesize, movements);
    }

    public PageResult getRecommendedMovements(Long userId, int page, int pagesize) {
        //获取存储在redis的key(可通过key来获取recommendPid)
        String redisKey = "MOVEMENTS_RECOMMEND_" + userId;
        String recommendPids = redisTemplate.opsForValue().get(redisKey);
        List<Movement> movementList = new ArrayList<>();
        if (StringUtils.isEmpty(recommendPids)) {
            movementList = movementApi.getMovementsRandomly(pagesize);
        } else {
            String[] recommendPid = recommendPids.split(",");
            int pages = recommendPid.length % pagesize == 0 ? recommendPid.length / pagesize : recommendPid.length / pagesize + 1;
            if (page <= pages) {
                List<Long> pids = Arrays.stream(recommendPid)
                        .skip((page - 1) * pagesize)
                        .limit(pagesize)
                        .map(e -> Convert.toLong(e))
                        .collect(Collectors.toList());
                movementList = movementApi.findMovementByPids(pids);
            }
        }
        return getPageResult(page, pagesize, movementList);
    }


    private PageResult getPageResult(Integer page, Integer pagesize, List<Movement> list) {
        if (CollUtil.isEmpty(list)) {
            return new PageResult(page,pagesize,0L,null);
        }
        //4、提取动态发布人的id列表
        List<Long> userIds = CollUtil.getFieldValues(list, "userId", Long.class);
        //5、根据用户的id列表获取用户详情
        Map<Long, UserInfo> map = userInfoApi.findByIds(userIds, null);
        //6、一个Movement构造一个vo对象
        List<MovementVo> vos = new ArrayList<>();
        for (Movement movement : list) {
            UserInfo userInfo = map.get(movement.getUserId());
            if (userInfo != null) {
                MovementVo vo = MovementVo.init(userInfo, movement);
                //修复点赞状态的bug，判断hashKey是否存在
                String key = Constants.MOVEMENTS_INTERACT_KEY + movement.getId().toHexString();
                String hashKey = Constants.MOVEMENT_LIKE_HASHKEY + UserHolder.getUserId();
                if (redisTemplate.opsForHash().hasKey(key, hashKey)) {
                    vo.setHasLiked(1);
                }
                vos.add(vo);
            }
        }
        //7、构造PageResult并返回
        return new PageResult(page, pagesize, 0L, vos);
    }

    public MovementVo findById(String movementId) {
        //根据movementId查询到movement
        Movement movement = movementApi.findByMovementId(movementId);
        //不为null
        if (movement != null) {
            //数据封装
            UserInfo userInfo = userInfoApi.findById(movement.getUserId());
            if (userInfo != null) {
                MovementVo vo=MovementVo.init(userInfo,movement);
                //修复点赞状态的bug，判断hashKey是否存在
                String key = Constants.MOVEMENTS_INTERACT_KEY + movement.getId().toHexString();
                String hashKey = Constants.MOVEMENT_LIKE_HASHKEY + UserHolder.getUserId();
                if (redisTemplate.opsForHash().hasKey(key, hashKey)) {
                    vo.setHasLiked(1);
                }
                return vo;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public List<VisitorsVo> visitors() {
        //1.从redis中获取当前用户查看访客的时间
        String key = Constants.VISITORS_USER;
        String hashKey = String.valueOf(UserHolder.getUserId());
        String value = (String) redisTemplate.opsForHash().get(key, hashKey);
        Long date = StringUtils.isEmpty(value) ? null:Long.valueOf(value);
        //2.获取最近的访客
        List<Visitors> visitors = visitorsApi.recentVisitors(date,UserHolder.getUserId());
        if (CollUtil.isEmpty(visitors)){
            //为null或为空,直接返回
            return new ArrayList<VisitorsVo>();
        }
        //3.获取访客用户的所有id信息
        List<Long> visitorUserId = CollUtil.getFieldValues(visitors, "visitorUserId", Long.class);
        Map<Long, UserInfo> map = userInfoApi.findByIds(visitorUserId, null);
        List<VisitorsVo> visitorsVoList = new ArrayList<>();
        for (Visitors visitor : visitors) {
            UserInfo userInfo = map.get(visitor.getVisitorUserId());
            if (userInfo!=null){
                //4.vo封装
                VisitorsVo vo = VisitorsVo.init(userInfo, visitor);
                //5.添加至list
                visitorsVoList.add(vo);
            }
        }
        redisTemplate.opsForHash().put(
                //key
                Constants.VISITORS_USER,
                //hashKey
                UserHolder.getUserId().toString(),
                //value
                Long.valueOf(System.currentTimeMillis()).toString()
        );
        //6.返回结果
        return visitorsVoList;
    }
}
