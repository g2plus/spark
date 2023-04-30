package cn.fiesacyum.dao.store;

import cn.fiesacyum.domain.store.Question;

import java.util.List;

public interface QuestionDao {
    int save(Question question);
    int delete(Question question);
    int update(Question question);
    Question findById(String id);
    List<Question> findAll();
}
