package cn.tanhua.model.mongo;

import org.bson.types.ObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "recommend_user")
public class RecommendUser implements Serializable {
    /**
     * 主键id
     */
    private ObjectId id;
    /**
     * 系统推荐给登录用户的其他用户的id
     */
    @Indexed
    private Long userId;
    /**
     * 登录用户的id
     */
    @Indexed
    private Long toUserId;
    /**
     * 推荐得分
     */
    private Double score =0D;
    /**
     * 日期
     */
    private String date;
}
