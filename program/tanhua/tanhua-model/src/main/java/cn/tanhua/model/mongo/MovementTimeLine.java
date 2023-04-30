package cn.tanhua.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 好友时间线表，用于存储好友发布的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movement_timeline")
public class MovementTimeLine implements Serializable {

    private static final long serialVersionUID = 9096178416317502524L;
    /**
     * 时间线表主键id
     */
    private ObjectId id;
    /**
     * 动态id
     */
    private ObjectId movementId;
    /**
     * 发布动态用户id
     */
    @Indexed
    private Long userId;
    /**
     * 好友id
     */
    @Indexed
    private Long friendId;
    /**
     *  发布的时间
     */
    private Long created;
}