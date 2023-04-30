package cn.tanhua.dubbo.api;

import cn.tanhua.model.mongo.Comment;

import java.util.List;

public interface CommentApi {

    Boolean hasComment(String movementId, Long userId, int type);

    Integer save(Comment comment);

    Integer delete(Comment comment);

    Integer likeThisComment(Comment comment);

    Integer dislikeThisComment(Comment comment);

    List<Comment> getCommentList(String movementId, int page, int pagesize);
}

