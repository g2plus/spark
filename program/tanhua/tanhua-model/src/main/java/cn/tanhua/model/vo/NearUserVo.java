package cn.tanhua.model.vo;


import cn.tanhua.model.pojo.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NearUserVo implements Serializable {

    private Integer userId;
    private String avatar;
    private String nickname;

    public static NearUserVo init(UserInfo userInfo) {
        NearUserVo nearUserVo = new NearUserVo();
        BeanUtils.copyProperties(userInfo,nearUserVo);
        nearUserVo.setUserId(Integer.parseInt(userInfo.getId().toString()));
        return nearUserVo;
    }
}
