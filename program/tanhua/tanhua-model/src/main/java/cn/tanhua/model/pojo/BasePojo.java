package cn.tanhua.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/*@Data相当于@Getter @Setter
@RequiredArgsConstructor @ToString
@EqualsAndHashCode这5个注解的合集*/

@Data
public abstract class BasePojo implements Serializable {

    //使用mybatisplus的来实现表单字段的填充，仅填充一次
    @TableField(fill= FieldFill.INSERT)
    private Date created;

    //使用mybatisplus的来实现表单字段的填充，新建与更新时使用
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updated;

}
