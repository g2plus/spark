package cn.tanhua.model.vo;

import cn.tanhua.model.mongo.Comment;
import cn.tanhua.model.pojo.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo implements Serializable {

    /**
     * 评论id
     */
    private String id;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 评论
     */
    private String content;
    /**
     * 评论时间
     */
    private String createDate;

    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 是否点赞（1是，0否）
     */
    private Integer hasLiked;

    public static CommentVo init(UserInfo userInfo, Comment item) {
        CommentVo vo = new CommentVo();
        BeanUtils.copyProperties(userInfo, vo);
        BeanUtils.copyProperties(item, vo);
        //初始化为0.如果被登录用户点赞,将被设置1
        vo.setHasLiked(0);
        Date date = new Date(item.getCreated());
        vo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        //转换类型用于显示数据
        vo.setId(item.getId().toHexString());
        return vo;
    }
}