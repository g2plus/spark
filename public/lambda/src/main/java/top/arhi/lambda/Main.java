package top.arhi.lambda;

import top.arhi.lambda.args.Caculator;
import top.arhi.lambda.args.Math;
import top.arhi.lambda.withoutargs.Cook;

import java.util.*;

/**
 * @author e2607
 * @version 1.0
 * @description: lambda
 * @date 11/29/2021 12:31 PM
 */
public class Main {
    public static void main(String[] args) {
        //testA();
        //testB();

        //testC();
        testD();


    }





    private static void testA() {
        //执行完任务后线程关闭
        Thread thread = new Thread(() -> System.out.println("Hello world!"));
        thread.start();
    }

    private static void testB() {
        //原理:使用lambda表达式实现抽象方法，实例化接口
        Cook cook = new Cook(() -> System.out.println("正在制作炒饭中......"));
        cook.start();
    }

    private static void testC() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入数字A:");
        int a = scanner.nextInt();
        System.out.print("请输入数字B:");
        int b = scanner.nextInt();
        //原理:使用lambda表达式实现抽象方法，实例化接口
        Caculator caculator = new Caculator((A, B)->A+B);
        //调用add方法实现求和
        int sum = caculator.add(a, b);
        System.out.println("数字A与数字B的和是"+sum);
    }

    private static void testD(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("ZhangSan",23));
        personList.add(new Person("Lisi",24));
        personList.add(new Person("Zhaoliu",25));
        personList.add(new Person("ZhouQi",27));

        personList.forEach(person-> System.out.println(person.getName()+"->"+person.getAge()));

        //list集合转换为数组的正确做法
        Person[] persons= personList.toArray(new Person[personList.size()]);
        //使用类型强制转换将导致类型转换异常

        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                //o1.getXxx-o2.getXxx 由低到高排序
                return o2.getAge()-o1.getAge();
            }
        });
        for (Person person : persons) {
            System.out.println(person);
        }

    }


}
