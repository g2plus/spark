package cn.fiesacyum.service.store;

import cn.fiesacyum.domain.store.Question;
import com.github.pagehelper.PageInfo;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface QuestionService {
    /**
     *
     * @param question
     * @String 图片的名称
     */
    String save(Question question,boolean uploadedFlag);

    /**
     *
     * @param question
     */
    void delete(Question question);

    /**
     *
     * @param question
     */
    void update(Question question,boolean uploadedFlag);

    /**
     *
     * @param id
     * @return
     */
    Question findById(String id);

    /**
     *
     * @return
     */
    List<Question> findAll();

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findAll(int pageNum, int pageSize);

    ByteArrayOutputStream getReport();
}
