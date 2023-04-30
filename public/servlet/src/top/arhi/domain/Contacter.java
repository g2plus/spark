package top.arhi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contacter {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String province;
    private String qq;
    private String email;
}
