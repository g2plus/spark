package cn.tanhua.model.vo;

import cn.tanhua.model.mongo.Visitors;
import cn.tanhua.model.pojo.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorsVo implements Serializable {

    private Long id;
    private String avatar;
    private String nickname;
    private String gender;
    private Integer age;
    private String[] tags;
    private Long fateValue;

    /**
     * 在vo对象中，补充一个工具方法，封装转化过程
     */
    public static VisitorsVo init(UserInfo userInfo, Visitors visitors) {
        VisitorsVo vo = new VisitorsVo();
        BeanUtils.copyProperties(userInfo,vo);
        if(userInfo.getTags() != null) {
            vo.setTags(userInfo.getTags().split(","));
        }
        vo.setFateValue(visitors.getScore().longValue());
        return vo;
    }
}