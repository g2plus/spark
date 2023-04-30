package cn.tanhua;

import cn.tanhua.dubbo.DubboApplication;
import cn.tanhua.dubbo.api.QuestionApi;
import cn.tanhua.dubbo.api.UserInfoApi;
import cn.tanhua.dubbo.mapper.QuestionMapper;
import cn.tanhua.dubbo.mapper.UserMapper;
import cn.tanhua.model.pojo.Question;
import cn.tanhua.model.pojo.UserInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes=DubboApplication.class)
@RunWith(SpringRunner.class)
public class Insert {
    @Autowired
    private QuestionMapper questionMapper;
    @Test
    public void testInsert(){
        Question question = new Question();
        question.setId(5L);
        question.setUserId(14L);
        question.setTxt("How are you doing?");
        questionMapper.insert(question);
    }
}
