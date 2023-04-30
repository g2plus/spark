package top.arhi.lambda.reference;

import top.arhi.lambda.reference.*;


/**
 * @author e2607
 * @version 1.0
 * @description: 方法引用
 * @date 12/3/2021 11:28 PM
 */
public class TestB{

    public static void main(String[] args) {

        //匿名内部类
        printStr(new Reference() {
            @Override
            public void print(String s) {
                System.out.println(s);
            }
        });

        //lambda表达式
        /**
         * 参数s，传递给System.out对象，调用println。
         * System.out对象存在
         * println方法存在
         * 可是方法引用来优化代码
         * */
        //s的数据类型为String
        printStr((s)-> System.out.println(s));


        //方法引用（可推导可省略）
        printStr(System.out::println);

        //基于对象的方法引用
        printStr(s->{
            ReferenceObject referenceObject = new ReferenceObject();
            referenceObject.printToUpperCase(s);
        });


        ReferenceObject referenceObject = new ReferenceObject();
        printStr(referenceObject::printToUpperCase);

    }

    private static void printStr(Reference reference){
        reference.print("Hello");
    }
}
