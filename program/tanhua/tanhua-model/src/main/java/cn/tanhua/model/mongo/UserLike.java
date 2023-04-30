package cn.tanhua.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_like")
public class UserLike implements Serializable {

    private static final long serialVersionUID = 6739966698394686523L;

    private ObjectId id;
    /**
     * 用户id，自己
     */
    @Indexed
    private Long userId;
    /**
     * 喜欢的用户id，对方
     */
    @Indexed
    private Long likeUserId;
    /**
     * 是否喜欢
     */
    private Boolean isLike;
    /**
     * 创建时间
     */
    private Long created;
    /**
     * 更新时间
     */
    private Long updated;

}