package cn.tanhua.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "video")
public class Video implements java.io.Serializable {

    private static final long serialVersionUID = -3136732836884933873L;

    /**
     * 主键id
     */
    private ObjectId id;
    /**
     * 自动增长
     */
    private Long vid;
    private Long created;


    private Long userId;
    private String text;
    private String picUrl;
    private String videoUrl;


    private Integer likeCount=0;
    private Integer commentCount=0;
    private Integer loveCount=0;
}
