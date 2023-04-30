package cn.tanhua.dubbo.api;

import cn.tanhua.dubbo.mapper.QuestionMapper;
import cn.tanhua.model.pojo.Question;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class QuestionApiImpl implements QuestionApi {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void saveQuestion(Question question) {
        questionMapper.insert(question);
    }

    @Override
    public Question findByUserId(Long userId) {
        QueryWrapper<Question> wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
        return questionMapper.selectOne(wrapper);
    }

    @Override
    public void updateQuestions(Question question) {
        questionMapper.updateById(question);
    }
}
