package top.arhi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lottery {
    private Long id;
    private Integer red1;
    private Integer red2;
    private Integer red3;
    private Integer red4;
    private Integer red5;
    private Integer red6;
    private Integer blue;
}
