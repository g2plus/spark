package cn.tanhua.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "visitors")
public class Visitors implements java.io.Serializable{

    private static final long serialVersionUID = 2811682148052386573L;

    private ObjectId id;
    /**
     * 来访时间
     */
    private Long date;
    /**
     * 我的id
     */
    private Long userId;
    /**
     * 来访用户id
     */
    private Long visitorUserId;
    /**
     * 得分
     */
    private Double score;
    /**
     * 来源，如首页、圈子等
     */
    private String from;
    /**
     * 来访日期
     */
    private String visitDate;


}