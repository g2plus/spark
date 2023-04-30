package cn.tanhua.dubbo.api;

import cn.tanhua.model.pojo.Question;

public interface QuestionApi {

    void saveQuestion(Question question);

    Question findByUserId(Long userId);

    void updateQuestions(Question question);

}
