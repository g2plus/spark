package top.arhi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author e2607
 * @version 1.0
 * @description: TODO
 * @date 12/4/2021 10:34 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    public String location;
    protected int age;
    private int height;
    int length;
}
