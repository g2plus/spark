package top.arhi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author e2607
 * @version 1.0
 * @description: TODO
 * @date 12/4/2021 10:33 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private String name;
    private Integer age;
    private Boolean flag;
}
