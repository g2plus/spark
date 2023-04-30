package cn.tanhua.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
    设计师设计的表字段名和我们实体设计的属性名不一致，
    我们可以通过@TableField的value来映射,一致时可以不写
    以及有些属性字段不需要映射到数据库，仅仅系统里临时用，或者记录等功能的时候，
    我们有可以通过@TableField的exist属性来配置；
    实体类的属性名和数据库的字段名 自动映射：
    1. 名称一样
    2. 数据库字段使用_分割，实体类属性名使用驼峰名称
    //@TableField("user_name")
    private String userName;
*/
//@TableName("tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BasePojo {

    private Long id;
    private String mobile;
    private String password;
    private String hxUser;
    private String hxPassword;

}
