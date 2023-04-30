package cn.tanhua.server.service;


import cn.hutool.core.collection.CollUtil;
import cn.tanhua.commons.utils.Constants;
import cn.tanhua.dubbo.api.CommentApi;
import cn.tanhua.dubbo.api.MovementApi;
import cn.tanhua.dubbo.api.UserInfoApi;
import cn.tanhua.model.enumeration.CommentType;
import cn.tanhua.model.mongo.Comment;
import cn.tanhua.model.pojo.UserInfo;
import cn.tanhua.model.vo.CommentVo;
import cn.tanhua.model.vo.ErrorResult;
import cn.tanhua.model.vo.PageResult;
import cn.tanhua.server.exception.BusinessException;
import cn.tanhua.server.interceptor.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class CommentService {

    @DubboReference
    private CommentApi commentApi;

    @DubboReference
    private MovementApi movementApi;

    @DubboReference
    private UserInfoApi userInfoApi;



    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public Integer likeThisMovement(String movementId) {
        //根据movementId,评论人id,评论类型在comment数据表中进行查找
        Boolean hasComment = commentApi.hasComment(movementId, UserHolder.getUserId(), CommentType.LIKE.getType());

        //如果已经点赞抛出异常,提前结束
        if(hasComment){
            throw new BusinessException(ErrorResult.likeError());
        }

        //没有点赞,保存comment信息表
        Comment comment = new Comment();
        comment.setId(ObjectId.get());
        comment.setPublishId(new ObjectId(movementId));
        comment.setCommentType(CommentType.LIKE.getType());

        comment.setUserId(UserHolder.getUserId());
        comment.setCreated(System.currentTimeMillis());

        //保存获取最近的点赞数目
        Integer likeCount=commentApi.save(comment);

        //dubbo模块没有redis包
        //拼接redis的key，将用户的点赞状态存入redis
        String key = Constants.MOVEMENTS_INTERACT_KEY + movementId;
        String hashKey = Constants.MOVEMENT_LIKE_HASHKEY + UserHolder.getUserId();
        redisTemplate.opsForHash().put(key,hashKey,"1");

        return likeCount;

    }

    public Integer dislikeThisMovement(String movementId) {

        //根据movementId,评论人id,评论类型在comment数据表中进行查找
        Boolean hasComment = commentApi.hasComment(movementId, UserHolder.getUserId(), CommentType.LIKE.getType());

        //如果存在记录说明用户点赞过,进行对记录的删除, 并将动态对应的点赞数减一
        if(hasComment){

            //构建Comment对象,删除comment记录信息
            Comment comment = new Comment();
            comment.setPublishId(new ObjectId(movementId));
            comment.setCommentType(CommentType.LIKE.getType());
            comment.setUserId(UserHolder.getUserId());

            Integer likeCount = commentApi.delete(comment);

            //拼接redis的key，删除点赞状态
            String key = Constants.MOVEMENTS_INTERACT_KEY + movementId;
            String hashKey = Constants.MOVEMENT_LIKE_HASHKEY + UserHolder.getUserId();
            redisTemplate.opsForHash().delete(key,hashKey);
            return likeCount;
        }else{
            throw new BusinessException(ErrorResult.dislikeError());
        }
    }

    public void commit(String movementId, String content) {
        Comment oneComment = new Comment();
        //信息填充,保存到数据库
        oneComment.setId(ObjectId.get());
        oneComment.setPublishId(new ObjectId(movementId));
        oneComment.setCommentType(CommentType.COMMENT.getType());
        oneComment.setContent(content);
        oneComment.setUserId(UserHolder.getUserId());

        oneComment.setCreated(System.currentTimeMillis());
        //保存记录到mongodb,调用mongoTemplate确定publishUserId
        Integer cnt = commentApi.save(oneComment);
        //记录日志,评论数无需返回
        log.info("commentCount="+cnt);
    }

    public PageResult getCommentList(String movementId, int page, int pagesize) {
        //1、调用API查询评论列表
        List<Comment> list = commentApi.getCommentList(movementId,page,pagesize);
        //2、判断list集合是否存在
        if(CollUtil.isEmpty(list)) {
            return new PageResult();
        }
        //3、提取所有的用户id,调用UserInfoAPI查询用户详情
        List<Long> userIds = CollUtil.getFieldValues(list, "userId", Long.class);
        Map<Long, UserInfo> map = userInfoApi.findByIds(userIds, null);
        //4、构造vo对象
        List<CommentVo> vos = new ArrayList<>();
        for (Comment comment : list) {
            UserInfo userInfo = map.get(comment.getUserId());
            if(userInfo != null) {
                CommentVo vo = CommentVo.init(userInfo, comment);
                //修复点赞状态的bug，判断hashKey是否存在
                String key = Constants.COMMENTS_INTERACT_KEY + comment.getId().toHexString();
                String hashKey = Constants.COMMENT_LIKE_HASHKEY + UserHolder.getUserId();
                if (redisTemplate.opsForHash().hasKey(key, hashKey)) {
                    vo.setHasLiked(1);
                }
                vos.add(vo);
            }
        }
        //5、构造返回值
        return new PageResult(page,pagesize,0L,vos);
    }

    public Integer likeThisComment(String commentId) {
        //根据commentId,评论人id,评论类型在comment数据表中进行查找
        Boolean hasComment = commentApi.hasComment(commentId, UserHolder.getUserId(), CommentType.LIKE.getType());
        //如果已经点赞抛出异常,提前结束
        if(hasComment){
            throw new BusinessException(ErrorResult.likeError());
        }
        //没有点赞,保存comment信息表
        Comment comment = new Comment();
        comment.setId(ObjectId.get());
        comment.setPublishId(new ObjectId(commentId));
        comment.setCommentType(CommentType.LIKE.getType());
        comment.setUserId(UserHolder.getUserId());
        comment.setCreated(System.currentTimeMillis());
        Integer likeCount=commentApi.likeThisComment(comment);
        //拼接redis的key，将用户的点赞状态存入redis
        String key = Constants.COMMENTS_INTERACT_KEY + commentId;
        String hashKey = Constants.COMMENT_LIKE_HASHKEY + UserHolder.getUserId();
        redisTemplate.opsForHash().put(key,hashKey,"1");
        return likeCount;

    }

    public Integer dislikeThisComment(String commentId) {
        //根据commentId,评论人id,评论类型在comment数据表中进行查找
        Boolean hasComment = commentApi.hasComment(commentId, UserHolder.getUserId(), CommentType.LIKE.getType());
        //如果存在记录,进行记录的删除,更新数据
        if(hasComment){
            //构建Comment对象,删除comment记录信息
            Comment comment = new Comment();
            comment.setPublishId(new ObjectId(commentId));
            comment.setCommentType(CommentType.LIKE.getType());
            comment.setUserId(UserHolder.getUserId());
            Integer likeCount = commentApi.dislikeThisComment(comment);
            //拼接redis的key，将用户的点赞状态存入redis
            String key = Constants.COMMENTS_INTERACT_KEY + commentId;
            String hashKey = Constants.COMMENT_LIKE_HASHKEY + UserHolder.getUserId();
            redisTemplate.opsForHash().delete(key,hashKey);
            return likeCount;
        }else{
            throw new BusinessException(ErrorResult.dislikeError());
        }
    }
}