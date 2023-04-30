package cn.fiesacyum.service.store;


import cn.fiesacyum.domain.store.Company;
import cn.fiesacyum.service.store.impl.CompanyServiceImpl;
import com.github.pagehelper.PageInfo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 测试类与测试方法的书写规范
 * 1.测试类：类名Test
 * 2.测试方法：test方法名
 */
public class CompanyServiceTest {
    private static CompanyService companyService=null;

    @BeforeClass
    public static void init(){
        companyService=new CompanyServiceImpl();
    }

    @Test
    public void testSave(){
        Company company=new Company();
        company.setCity("changsha");
        companyService.save(company);
    }

    @Test
    public void testFindAll(){
        PageInfo all = companyService.findAll(1, 45);
        System.out.println(all);
    }

    @AfterClass
    public static void destroy(){
        //释放资源，等待垃圾回收器回收
        companyService=null;
    }
}
