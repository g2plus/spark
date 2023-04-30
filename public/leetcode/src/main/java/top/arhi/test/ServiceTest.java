package top.arhi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @author e2607
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Test
    public void test001() {
        Random random = new Random();
        String[] str={"0","1","2","3","4","5","6","7","8","9"};
        StringBuilder strBuilder=new StringBuilder();
        for(int i=0;i<6;i++){
            strBuilder.append(str[random.nextInt(str.length)]);
        }
        String s = strBuilder.toString();
        System.out.println(s);
    }
}
