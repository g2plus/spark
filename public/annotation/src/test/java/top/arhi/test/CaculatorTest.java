package top.arhi.test;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author e2607
 * @version 1.0
 * @description: 使用自定义的注解对方法进行测试，并记录方法执行错误的原因
 * @date 12/5/2021 5:22 PM
 */
public class CaculatorTest {

    public static void main(String[] args) throws IOException {
        //1.创建需要测试的对象
        Caculator caculator = new Caculator();
        //2.获取字节文件对象
        Class<? extends Caculator> aClass = caculator.getClass();
        //3.根据字节文件对象获取所有方法
        Method[] methods = aClass.getMethods();


        int number = 0;//统计异常次数
        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));
        for (Method method : methods) {
            //4.判断方法上是否有自定义注解
            if(method.isAnnotationPresent(Check.class)){
                //5.有则执行
                try {
                    method.invoke(caculator);
                } catch (Exception e) {
                    //6.捕获异常，记录到文件中
                    bw.write(method.getName()+"出现异常");
                    bw.newLine();
                    bw.write("异常名称:"+e.getCause().getClass().getName());
                    bw.newLine();
                    bw.write("异常原因:"+e.getCause().getMessage());
                    bw.newLine();
                    number++;
                    bw.write("------------------------------------");
                    bw.newLine();
                    bw.flush();
                }
            }
        }
        bw.write("本次测试共出现"+number+"次异常");
        bw.flush();
        bw.close();
    }
}
