package cn.fiesacyum.service.store;

import cn.fiesacyum.domain.store.Catalog;
import cn.fiesacyum.domain.store.Course;
import cn.fiesacyum.service.store.impl.CatalogServiceImpl;
import cn.fiesacyum.service.store.impl.CourseServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class CatalogServiceTest {
    private static CatalogService catalogService=null;

    @BeforeClass
    public static void init(){
        catalogService=new CatalogServiceImpl();
    }

    @Test
    public  void testSave(){
        Catalog catalog=new Catalog();
        Course course=new Course();
        course.setName("图像处理");
        CourseServiceImpl courseService = new CourseServiceImpl();
        courseService.save(course);
        catalog.setCourse(course);
        catalogService.save(catalog);
    }

    @Test
    public void testFindAll(){
        List<Catalog> all = catalogService.findAll();
        System.out.println(all);
    }

    @AfterClass
    public static void destroy(){
        catalogService=null;
    }

}
