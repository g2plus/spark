package cn.tanhua.dubbo.api;

import cn.tanhua.model.enumeration.CommentType;
import cn.tanhua.model.mongo.Comment;
import cn.tanhua.model.mongo.Movement;
import com.mongodb.client.result.DeleteResult;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.omg.CORBA.ORB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;



@DubboService
public class CommentApiImpl implements CommentApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Boolean hasComment(String movementId, Long userId, int type) {

        Criteria criteria=Criteria.where("publishId").is(new ObjectId(movementId))
                .and("commentType").is(type)
                .and("userId").is(userId);
        Query query = Query.query(criteria);
        return mongoTemplate.exists(query, Comment.class);
    }

    @Override
    public Integer save(Comment comment) {
        Movement movement = mongoTemplate.findById(comment.getPublishId(), Movement.class);
        if(movement!=null){
            //在dubbo层调用mongoTemplate工具,获取到movement对象,并知识comment的publishUserId
            comment.setPublishUserId(movement.getUserId());
        }

        mongoTemplate.save(comment);

        //查询更新的对应类型的count
        Query query = Query.query(Criteria.where("id").is(movement.getId()));

        Update update = new Update();

        if(comment.getCommentType() == CommentType.LIKE.getType()) {
            update.inc("likeCount",1);
        }else if (comment.getCommentType() == CommentType.COMMENT.getType()){
            update.inc("commentCount",1);
        }else {
            update.inc("loveCount",1);
        }

        //更新参数
        FindAndModifyOptions options = new FindAndModifyOptions();

        //获取更新后的最新数据
        options.returnNew(true) ;


        Movement modify = mongoTemplate.findAndModify(query, update, options, Movement.class);

        //获取评论的类型返回对应的最新评论数量，并返回
        return modify.statisCount(comment.getCommentType());

    }

    @Override
    public Integer delete(Comment comment) {

        //构建删除条件
        Criteria criteria = Criteria.where("publishId").is(comment.getPublishId())
                .and("commentType").is(comment.getCommentType())
                .and("userId").is(comment.getUserId());
        Query query = Query.query(criteria);

        //进行删除
        mongoTemplate.remove(query, Comment.class);


        //构建query,然后将点赞的记录总数-1,comment记录中对应的publishI的对应动态记录的的主键id
        Query removelikeQuery = Query.query(Criteria.where("id").is(comment.getPublishId()));

        Update update = new Update();

        if(comment.getCommentType() == CommentType.LIKE.getType()) {
            update.inc("likeCount",-1);
        }else if (comment.getCommentType() == CommentType.COMMENT.getType()){
            update.inc("commentCount",-1);
        }else {
            update.inc("loveCount",-1);
        }
        //更新参数
        FindAndModifyOptions options = new FindAndModifyOptions();

        //获取更新后的最新数据
        options.returnNew(true);

        Movement modify = mongoTemplate.findAndModify(removelikeQuery, update, options, Movement.class);

        //获取评论的类型返回对应的最新评论数量，并返回
        return modify.statisCount(comment.getCommentType());

    }

    @Override
    public Integer likeThisComment(Comment comment) {
        Comment likedComment = mongoTemplate.findById(comment.getPublishId(), Comment.class);
        if(likedComment!=null){
            comment.setPublishUserId(likedComment.getUserId());
        }
        mongoTemplate.save(comment);
        //查询更新的点赞数
        Query query = Query.query(Criteria.where("id").is(likedComment.getId()));
        Update update = new Update();

        update.inc("likeCount",1);

        //更新参数
        FindAndModifyOptions options = new FindAndModifyOptions();
        //获取更新后的最新数据
        options.returnNew(true) ;
        Comment modify = mongoTemplate.findAndModify(query, update, options, Comment.class);
        //获取评论的类型返回对应的最新评论数量，并返回
        return modify.statisCount(comment.getCommentType());
    }

    @Override
    public Integer dislikeThisComment(Comment comment) {
        //构建查询条件
        Query query = Query.query(Criteria.where("publishId").is(comment.getPublishId())
                        .and("userId").is(comment.getUserId())
                        .and("commentType").is(comment.getCommentType()));
        //进行删除
        mongoTemplate.remove(query, Comment.class);

        //构建query,然后将点赞的记录总数-1
        Query removeLikeQuery = Query.query(Criteria.where("id").is(comment.getPublishId()));
        Update update = new Update();
        update.inc("likeCount",-1);
        //更新参数
        FindAndModifyOptions options = new FindAndModifyOptions();
        //获取更新后的最新数据
        options.returnNew(true);
        Comment modify = mongoTemplate.findAndModify(removeLikeQuery, update, options, Comment.class);
        //获取评论的类型返回对应的最新评论数量，并返回
        return modify.statisCount(comment.getCommentType());

    }

    @Override
    public List<Comment> getCommentList(String movementId, int page, int pagesize) {

        Criteria criteria= Criteria.where("publishId").is(new ObjectId(movementId)).
                and("commentType").is(CommentType.COMMENT.getType());

        Query query = Query.query(criteria).with(Sort.by(Sort.Order.desc("created")))
                .skip((page-1)*pagesize)
                .limit(pagesize);

        List<Comment> comments = mongoTemplate.find(query, Comment.class);

        return comments;

    }
}
