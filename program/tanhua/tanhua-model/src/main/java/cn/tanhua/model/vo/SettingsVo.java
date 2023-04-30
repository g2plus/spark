package cn.tanhua.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingsVo implements Serializable {

    private Long id;
    private String strangerQuestion = "";
    private String phone;
    /**
     * 构建默认数据返回前端,避免前端解析null
     */
    private Boolean likeNotification = true;
    private Boolean pinglunNotification = true;
    private Boolean gonggaoNotification = true;

}
