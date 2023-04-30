package cn.fiesacyum.dao.store;

import cn.fiesacyum.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemDao {
    int save(QuestionItem questionItem);
    int delete(QuestionItem questionItem);
    int update(QuestionItem questionItem);
    QuestionItem findById(String id);
    List<QuestionItem> findByQuestionId(String questionId);
    int deleteByQuestionId(String questionId);
}
