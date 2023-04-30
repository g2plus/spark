package cn.tanhua.model.vo;

import cn.tanhua.model.mongo.RecommendUser;
import cn.tanhua.model.pojo.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodayBest {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户头像url地址
     */
    private String avatar;

    private String nickname;
    private String gender;
    private Integer age;
    private String[] tags;
    /**
     * 缘分值
     */
    private Long fateValue;


    public static TodayBest init(RecommendUser recommendUser, UserInfo userInfo) {
        //创建TodayBest对象
        TodayBest todayBest = new TodayBest();
        //使用工具进行属性copy(同名同类型)，id,avatar,nickname,gender,age
        BeanUtils.copyProperties(userInfo,todayBest);
        //int Integer， double Double，装箱与拆箱
        //获取fateValue
        todayBest.setFateValue((long)recommendUser.getScore().doubleValue());
        if(userInfo.getTags()!=null){
            todayBest.setTags(userInfo.getTags().split(","));
        }
        //返回对像
        return todayBest;
    }
}
