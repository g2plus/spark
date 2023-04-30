package cn.tanhua.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends BasePojo {

    /**
     * 避免id自增
     */
    @TableId(type= IdType.INPUT)
    private Long id;
    private String nickname;
    /**
     * 头像对应的url地址信息
     */
    private String avatar;
    private String tags;
    private String gender;
    private Integer age;
    private String education;
    private String city;
    private String birthday;
    private String coverPic;
    private String profession;
    private String income;
    private Integer marriage;
    /**
     * 用户状态,1为正常，2为冻结
     */
    @TableField(exist = false)
    private String userStatus = "1";
}
