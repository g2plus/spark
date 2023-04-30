package cn.tanhua.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 好友表:好友关系表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "friend")
public class Friend implements Serializable {

    private static final long serialVersionUID = 6003135946820874230L;
    private ObjectId id;
    /**
     * 用户id
     */
    @Indexed
    private Long userId;
    /**
     * 好友id
     */
    @Indexed
    private Long friendId;
    /**
     * 时间
     */
    private Long created;

}
