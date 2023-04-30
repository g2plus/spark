package top.arhi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author e2607
 * @version 1.0
 * @description: TODO
 * @date 12/4/2021 6:36 PM
 */
@Data
/**
 * @Builder可用与精确设设置成员变量的值，不能被重写。
 * https://www.jianshu.com/p/d08e255312f9
 * 一般在设置页面错误码时使用
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private int age;

    public void sleep(){
        System.out.println("sleeping...");
    }

    public void eat(){
        System.out.println("eating...");
    }
}
