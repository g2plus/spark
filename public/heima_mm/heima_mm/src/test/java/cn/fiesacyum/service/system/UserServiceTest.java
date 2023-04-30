package cn.fiesacyum.service.system;

import cn.fiesacyum.domain.system.Dept;
import cn.fiesacyum.service.system.impl.DeptServiceImpl;
import com.github.pagehelper.PageInfo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceTest {
    private static DeptService userService=null;

    @BeforeClass
    public static void init(){
        userService=new DeptServiceImpl();
    }

    @Test
    public void testSave(){
        Dept dept=new Dept();
        dept.setState(1);
        userService.save(dept);
    }

    @Test
    public void testFindAll(){
        PageInfo all = userService.findAll(1, 45);
        System.out.println(all);
    }

    @AfterClass
    public static void destroy(){
        //释放资源，等待垃圾回收器回收
        userService=null;
    }
    
}
