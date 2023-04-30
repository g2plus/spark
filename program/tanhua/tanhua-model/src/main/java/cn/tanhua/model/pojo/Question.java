package cn.tanhua.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BasePojo{

    private Long id;
    private Long userId;
    private String txt;

}
