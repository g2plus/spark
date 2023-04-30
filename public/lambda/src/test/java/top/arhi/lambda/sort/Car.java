package top.arhi.lambda.sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author e2607
 * @version 1.0
 * @description: 实现Comparable接口实现排序
 * @date 11/29/2021 3:13 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Comparable<Car> {
    private String brand;
    private Double price;
    private Double star;


    /***
     * this.getXxx-o.getXxx
     * 1:默认排序，从小到大
     * -1: 从大到小
     * compareTo 返回值为1，表示从小到大开始排序
     * -1 表示（倒叙排列）排列
     */
    @Override
    public int compareTo(Car o) {
        double i = this.getPrice()-o.getPrice();
        if(i>0){
            return 1;
        }else{
            //如果价格相同
            if(i==0){
                //按照分数分数由高到低排序
                double j = this.getStar()-o.getStar();
                    if(j>0){
                    return -1;
                }else{
                    return 1;
                }
            }
        }
        return -1;
    }
}
