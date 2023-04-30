package top.arhi.test;

/**
 * @author e2607
 * @version 1.0
 * @description: TODO
 * @date 12/5/2021 5:16 PM
 */
public class Caculator {

    @Check
    public void add(){
        System.out.println("1+0="+(1+0));
    }

    @Check
    public void sub(){
        System.out.println("1-0="+(1-0));
    }

    @Check
    public void multiply(){
        System.out.println("2*2="+(2*2));
    }

    @Check
    public void div(){
        System.out.println("1/0="+(1/0));
    }

    public void show(){
        System.out.println("Are you Happy?");
    }
}
