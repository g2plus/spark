package cn.tanhua.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendUserDto implements Serializable {
    /**
     * 当前页数
     */
    private Integer page = 1;
    /**
     * 页面显示条数
     */
    private Integer pagesize = 10;
    private String gender;
    private String lastLogin;
    private Integer age;
    private String city;
    private String education;
}
