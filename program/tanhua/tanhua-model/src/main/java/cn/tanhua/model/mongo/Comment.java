package cn.tanhua.model.mongo;

import cn.tanhua.model.enumeration.CommentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comment")
public class Comment implements Serializable {
    /**
     * 评论记录主键id
     */
    private ObjectId id;
    /**
     * 动态id
     */
    private ObjectId publishId;
    /**
     * 评论类型
     */
    private Integer commentType;
    /**
     * 评论类型为评论时,进行赋值
     */
    private String content;
    /**
     * 评论人的用户id
     */
    @Indexed
    private Long userId;
    /**
     * 被评论的用户id
     */
    @Indexed
    private Long publishUserId;
    /**
     * 评论创建时间
     */
    private Long created;
    /**
     * 点赞数量初始为0
     */
    private Integer likeCount = 0;

    public Integer statisCount(Integer commentType) {
        if (commentType == CommentType.LIKE.getType()){
            return this.likeCount;
        }else{
            return null;
        }
    }

}
