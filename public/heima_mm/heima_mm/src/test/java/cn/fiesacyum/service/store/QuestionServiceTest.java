package cn.fiesacyum.service.store;

import cn.fiesacyum.domain.store.Question;
import cn.fiesacyum.service.store.impl.QuestionServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class QuestionServiceTest {
    private static QuestionService questionService=null;

    @BeforeClass
    public static void init(){
        questionService=new QuestionServiceImpl();
    }

    @Test
    public void testFindAll(){
        List<Question> all = questionService.findAll();
        System.out.println(all);
    }

    @AfterClass
    public static void destroy(){
        questionService=null;
    }

}
