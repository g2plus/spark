package top.arhi.lambda.reference;

/**
 * @author e2607
 * @version 1.0
 * @description: TODO
 * @date 12/4/2021 3:41 PM
 */
public class Man extends Human {

    @Override
    public void sayHello(){
        System.out.println("Hello,I'm man!");
    }
    public void show() {
        method(new Greet() {
            @Override
            public void greet() {
                Human human = new Human();
                human.sayHello();
            }
        });
        System.out.println("-------------------");
        //super，sayHello方法已存在
        method(super::sayHello);
        //this,sayHello方法已存在
        System.out.println("-------------------");
        method(this::sayHello);
    }

    public void method(Greet greet){
        greet.greet();
    }
}
