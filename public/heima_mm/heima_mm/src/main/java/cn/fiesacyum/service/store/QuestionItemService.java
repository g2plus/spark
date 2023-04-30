package cn.fiesacyum.service.store;

import cn.fiesacyum.domain.store.QuestionItem;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface QuestionItemService {
    /**
     *
     * @param questionItem
     */
    String save(QuestionItem questionItem,String questionId/*,boolean uploadedFlag*/);

    /**
     *
     * @param questionItem
     */
    void delete(QuestionItem questionItem);

    /**
     *
     * @param questionItem
     */
    void update(QuestionItem questionItem/*,boolean uploadedFlag*/);

    /**
     *
     * @param id
     * @return
     */
    QuestionItem findById(String id);

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findAll(String questionId,int pageNum, int pageSize);


    List<QuestionItem> findByQuestionId(String questionId);


    void deleteByQuestionId(String questionId);
}
