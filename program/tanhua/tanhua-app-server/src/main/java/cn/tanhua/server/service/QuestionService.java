package cn.tanhua.server.service;

import cn.tanhua.dubbo.api.QuestionApi;
import cn.tanhua.model.pojo.Question;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @DubboReference
    private QuestionApi questionApi;


    public void saveOrUpdateQuestions(Long userId, String content) {
        //根据用户id从数据表tb_questions中查询数据]
        Question question = questionApi.findByUserId(userId);
        if(null==question){
            question = new Question();
            question.setUserId(userId);
            question.setTxt(content);
            questionApi.saveQuestion(question);
        }else{
            question.getId();
            question.setTxt(content);
            questionApi.updateQuestions(question);
        }
    }
}
