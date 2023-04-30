package cn.tanhua.model.vo;

import cn.tanhua.model.mongo.Movement;
import cn.tanhua.model.pojo.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementVo implements Serializable {

    /**
     * 动态id
     */
    private String id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别
     */
    private String gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 标签
     */
    private String[] tags;
    /**
     * 文字动态
     */
    private String textContent;
    private String[] imageContent;
    private String distance;
    /**
     * 将毫秒值转换为Date然后转换为String
     */
    private String createDate;

    private Integer likeCount;
    private Integer commentCount;
    private Integer loveCount;

    private Integer hasLiked;
    private Integer hasLoved;

    public static MovementVo init(UserInfo userInfo, Movement movement){

        MovementVo vo = new MovementVo();
        //设置动态数据
        BeanUtils.copyProperties(movement, vo);
        vo.setId(movement.getId().toHexString());
        //设置用户数据
        BeanUtils.copyProperties(userInfo, vo);
        if(!StringUtils.isEmpty(userInfo.getTags())) {
            vo.setTags(userInfo.getTags().split(","));
        }
        vo.setUserId(userInfo.getId().intValue());
        //图片列表
        vo.setImageContent(movement.getMedias().toArray(new String[]{}));
        //距离
        vo.setDistance("500米");
        Date date = new Date(movement.getCreated());
        vo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        //设置是否点赞(后续通过读取redis中的数字来进行设置的再次设置)
        vo.setHasLoved(0);
        vo.setHasLiked(0);
        return vo;
    }
}