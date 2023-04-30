package cn.fiesacyum.service.system;


import cn.fiesacyum.domain.system.Dept;
import cn.fiesacyum.service.system.impl.DeptServiceImpl;
import com.github.pagehelper.PageInfo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class DeptServiceTest {
    private static DeptService deptService=null;

    @BeforeClass
    public static void init(){
        deptService=new DeptServiceImpl();
    }

    @Test
    public void testSave(){
        Dept dept=new Dept();
        dept.setState(1);
        deptService.save(dept);
    }

    @Test
    public void testFindAll(){
        //PageInfo all = deptService.findAll(1, 45);
        List<Dept> all = deptService.findAll();
        System.out.println(all);
    }

    @AfterClass
    public static void destroy(){
        //释放资源，等待垃圾回收器回收
        deptService=null;
    }
}
