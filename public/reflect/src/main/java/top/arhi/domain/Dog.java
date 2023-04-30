package top.arhi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author e2607
 * @version 1.0
 * @description: TODO
 * @date 12/4/2021 10:35 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog extends Animal {
    public int color;
    protected int name;
    private String type;
    int fur;
}
