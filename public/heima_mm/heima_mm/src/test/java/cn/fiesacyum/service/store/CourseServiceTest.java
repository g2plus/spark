package cn.fiesacyum.service.store;

import cn.fiesacyum.domain.store.Course;
import cn.fiesacyum.service.store.impl.CourseServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class CourseServiceTest {
    private static CourseService courseService=null;

    @BeforeClass
    public static void init(){
        courseService=new CourseServiceImpl();
    }

    @Test
    public void testSave(){
        Course course = new Course();
        course.setName("图像处理");
        courseService.save(course);
    }

    @Test
    public void testFindAll(){
        List<Course> all = courseService.findAll();
        System.out.println(all);
    }

    @AfterClass
    public static void destroy(){
        courseService=null;
    }
}
