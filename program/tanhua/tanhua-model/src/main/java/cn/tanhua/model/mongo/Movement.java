package cn.tanhua.model.mongo;

import cn.tanhua.model.enumeration.CommentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

//动态详情表
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movement")
public class Movement implements Serializable {

    /**
     * 主键id 自动增长
     */
    private ObjectId id;
    /**
     * mongodb不支持数据自增，可使用redis或则实现
     * Long类型，用于推荐系统的模型(自动增长)
     */
    private Long pid;
    /***
     * 发布动态的用户id
     */
    @Indexed
    private Long userId;
    /**
     * 文字
     */
    private String textContent;
    /**
     * 媒体数据，图片或小视频 url
     */
    private List<String> medias;
    /**
     * 状态 0：未审（默认），1：通过，2：驳回
     */
    private Integer state = 0;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     *进行字段映射，前端提交的字段为location，mongodb的字段为locationName
     * 位置名称
     */
    @Field(name="locationName")
    private String location;
    /**
     * 发布时间
     */
    private Long created;
    /**
     * 设置默认值为0并保存至数据库
     */
    private Integer commentCount = 0;
    private Integer likeCount = 0;
    private Integer loveCount = 0;

    public Integer statisCount(Integer commentType) {
        //引用类型的比较不推荐使用==(引用类型比较的是地址),此处type的类型为int可以使用==
        if (commentType == CommentType.LIKE.getType()) {
            return this.likeCount;
        } else if (commentType == CommentType.COMMENT.getType()) {
            return this.commentCount;
        } else {
            return this.loveCount;
        }
    }
}
