package top.arhi.lambda;

import static org.junit.Assert.*;
/***
 * 断言类Assert
 * 以上使用静态进行导入的原因：however, they read better if they
 *  * are referenced through static import:
 *  *
 *  * <pre>
 *  * import static org.junit.Assert.*;
 *  *    ...
 *  *    assertEquals(...);
 *  * </pre>
 */
import org.junit.Test;
import top.arhi.lambda.args.Caculator;
import top.arhi.lambda.reference.Reference;
import top.arhi.lambda.reference.ReferenceClass;
import top.arhi.lambda.reference.ReferenceObject;
import top.arhi.lambda.sort.Car;
import top.arhi.lambda.sort.Person;
import top.arhi.lambda.withoutargs.Cook;

import java.util.*;

/**
 * @author e2607
 * @version 1.0
 * @description: 引入junit进行单元测试
 * @date 11/29/2021 12:31 PM
        */
public class DailyTest {

    //junit进行单元测试的原理是反射,单元测试方法必须使用public进行对外暴露，
    //静态方法上不能使用@Test注解，接口作为参数的方法不能使用@Test注解
    //junit将创建测试类对象，然后调用该方法运行
    //junit断言类Assert类，

    @Test
    public void testA() {
        //执行完任务后线程关闭
        Thread thread = new Thread(() -> System.out.println("Hello world!"));
        thread.start();
        //静态方法上不能使用@Test注解
        staticTest();
    }

    //@Test
    public static void staticTest(){
        System.out.println("嵌套测试");
    }


    @Test
    public  void testB() {
        //原理:使用lambda表达式实现抽象方法，实例化接口
        Cook cook = new Cook(() -> System.out.println("正在制作炒饭中......"));
        cook.start();
    }

    @Test
    public void testC() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入数字A:");
        int a = scanner.nextInt();
        System.out.print("请输入数字B:");
        int b = scanner.nextInt();
        //原理:使用lambda表达式实现抽象方法，实例化接口
        Caculator caculator = new Caculator((A, B)->A+B);
        //调用add方法实现求和
        int sum = caculator.add(a, b);
        //进行断言，预期值与实际值相同测试通过。
        assertEquals(300,sum);
    }

    @Test
    public void testD(){
        List<top.arhi.lambda.sort.Person> personList = new ArrayList<>();
        personList.add(new top.arhi.lambda.sort.Person("ZhangSan",23));
        personList.add(new top.arhi.lambda.sort.Person("Lisi",24));
        personList.add(new top.arhi.lambda.sort.Person("Zhaoliu",25));
        personList.add(new top.arhi.lambda.sort.Person("ZhouQi",27));

        personList.forEach(person-> System.out.println(person.getName()+"->"+person.getAge()));

        //list集合转换为数组的正确做法
        top.arhi.lambda.sort.Person[] persons= personList.toArray(new top.arhi.lambda.sort.Person[personList.size()]);
        //使用类型强制转换将导致类型转换异常

        Arrays.sort(persons,
           (top.arhi.lambda.sort.Person o1, top.arhi.lambda.sort.Person o2) ->
                //o1.getXxx-o2.getXxx 由低到高排序
                o2.getAge()-o1.getAge()
        );
        for (Person person : persons) {
            System.out.println(person);
        }
    }


    @Test
    public void testE(){
        Car[] cars = new Car[]{
                new Car("奔驰",10000.00,4.9),
                new Car("大众",10000.00,5.0),
                new Car("宝马",14000.00,4.9),
                new Car("本田",14000.00,5.0),
                new Car("丰田",12500.00,4.6),
        };
        //数组转换为集合的方法
        List<Car> carList = Arrays.asList(cars);
        Collections.sort(carList);
        carList.forEach(car-> System.out.println(car));
    }


    //方法引用测试
    //对象，方法已存在才能使用方法引用，可推导可省略
    @Test
    public void testF(){
        testReference((s)-> System.out.println(s));
        System.out.println("----------------------------------");
        testReference(System.out::println);
    }


    //基于对象名的方法引用
    @Test
    public void testG(){
        testReference((s)->{
            ReferenceObject referenceObject = new ReferenceObject();
            referenceObject.printToUpperCase(s);
        });
        System.out.println("----------------------------------");
        ReferenceObject referenceObject = new ReferenceObject();
        testReference(referenceObject::printToUpperCase);

    }

    //基于类名的方法引用
    @Test
    public void testH(){
        testReference((s)->{
            ReferenceClass.printToUpperCase(s);
        });
        System.out.println("----------------------------------");
        testReference(ReferenceClass::printToUpperCase);

    }


    //使用super来进行方法引用
    @Test
    public void testI(){
        System.out.println("Hello World");
    }

    //此方法不能使用@Test注解,Reference接口方法代理。
    public static void testReference(Reference reference){
        reference.print("Hello World!");
    }
}
