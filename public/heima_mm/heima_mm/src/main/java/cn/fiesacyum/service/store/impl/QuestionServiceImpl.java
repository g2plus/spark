package cn.fiesacyum.service.store.impl;

import cn.fiesacyum.dao.store.QuestionDao;
import cn.fiesacyum.domain.store.Question;
import cn.fiesacyum.factory.MapperFactory;
import cn.fiesacyum.service.store.QuestionService;
import cn.fiesacyum.utils.FileUtil;
import cn.fiesacyum.utils.TransactionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class QuestionServiceImpl implements QuestionService {

    @Override
    public String save(Question question,boolean uploadedFlag) {
        SqlSession sqlSession=null;
        String id=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            id = UUID.randomUUID().toString();
            question.setId(id);
            /*
            *  <c:choose>
                    <c:when test="${o.reviewStatus eq '1'}"><font color="green">审核通过</font></c:when>
                    <c:when test="${o.reviewStatus eq '0'}">审核中</c:when>
                    <c:when test="${o.reviewStatus eq '-1'}"><font color="red">审核不通过</font></c:when>
               </c:choose>
            */
            //设置默认的审核状态为审核中
            question.setReviewStatus("0");
            question.setCreateTime(new Date());
            if(uploadedFlag){
                //如果上传图片,则设置图片的名称为id。
                question.setPicture(id);
            }
            mapper.save(question);
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       return id;
    }

    @Override
    public void delete(Question question) {
        SqlSession sqlSession=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            mapper.delete(question);
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Question question,boolean uploadedFlag) {
        SqlSession sqlSession=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            if(uploadedFlag){
                question.setPicture(question.getId());
            }
            mapper.update(question);
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Question findById(String id) {
        SqlSession sqlSession=null;
        Question question=null;
        try{
            sqlSession= MapperFactory.getSqlSession();
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            question = mapper.findById(id);
        }catch(Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return question;
    }

    @Override
    public List<Question> findAll() {
        SqlSession sqlSession=null;
       List<Question> list=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            list = mapper.findAll();
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public PageInfo findAll(int pageNum, int pageSize) {
        SqlSession sqlSession=null;
        PageInfo pageInfo=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            Page page= PageHelper.startPage(pageNum,pageSize);
            List<Question> all= mapper.findAll();
            pageInfo=new PageInfo(all);
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return pageInfo;
    }

    public ByteArrayOutputStream getReport(){
        SqlSession sqlSession=null;
        ByteArrayOutputStream os=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            List<Question> all = mapper.findAll();
            os = FileUtil.exportFile(all);
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return os;
    }
}
