package top.arhi.lambda.args;

/**
 * @author e2607
 * @version 1.0
 * @description: TODO
 * @date 11/29/2021 1:33 PM
 */
public class Caculator {

    private Math math;


    public Caculator(Math math){
        this.math=math;
    }

    public int add(int a, int b){
       return math.sum(a, b);
    }
}
