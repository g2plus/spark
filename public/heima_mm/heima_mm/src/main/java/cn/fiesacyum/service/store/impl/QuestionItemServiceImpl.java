package cn.fiesacyum.service.store.impl;

import cn.fiesacyum.dao.store.QuestionItemDao;
import cn.fiesacyum.domain.store.QuestionItem;
import cn.fiesacyum.factory.MapperFactory;
import cn.fiesacyum.service.store.QuestionItemService;
import cn.fiesacyum.utils.TransactionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.omg.CORBA.TRANSACTION_UNAVAILABLE;

import java.util.List;
import java.util.UUID;

public class QuestionItemServiceImpl implements QuestionItemService {
    @Override
    public String save(QuestionItem questionItem, String questionId/*,boolean uploadedFlag*/) {
        //事务的处理方式！！！
        SqlSession sqlSession=null;
        String id=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            QuestionItemDao mapper = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            //保存
            //前端不进行id的设置，需要对questionItem的id进行设置
            id= UUID.randomUUID().toString();
            questionItem.setId(id);
            questionItem.setQuestionId(questionId);
            /*if(uploadedFlag){
                questionItem.setPicture(id);
            }*/
            mapper.save(questionItem);
            //提交事务
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return id;
    }


    @Override
    public void delete(QuestionItem questionItem) {
        SqlSession sqlSession=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            QuestionItemDao mapper = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            //删除
            mapper.delete(questionItem);
            //提交事务
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public void update(QuestionItem questionItem/*,boolean uploadedFlag*/) {
        SqlSession sqlSession=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            QuestionItemDao mapper = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            //更新
            /*if(uploadedFlag){
                questionItem.setPicture(questionItem.getId());
            }*/
            mapper.update(questionItem);
            //提交事务
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public QuestionItem findById(String id) {
        SqlSession sqlSession=null;
        QuestionItem questionItem=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            QuestionItemDao mapper = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            questionItem=mapper.findById(id);
        }catch(Exception e){
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return questionItem;
    }

    @Override
    public PageInfo findAll(String questionId,int pageNum, int pageSize){
        Page page=PageHelper.startPage(pageNum,pageSize);
        List<QuestionItem> all = findByQuestionId(questionId);
        PageInfo pageInfo=new PageInfo(all);
        return pageInfo;
    }

    @Override
    public List<QuestionItem> findByQuestionId(String questionId) {
        SqlSession sqlSession=null;
        List<QuestionItem> list=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            QuestionItemDao mapper = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            list= mapper.findByQuestionId(questionId);
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try {
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    //当进行对题目进行删除时，删除题目的选项。
    @Override
    public void deleteByQuestionId(String questionId) {
        SqlSession sqlSession=null;
        try{
            sqlSession= MapperFactory.getSqlSession();
            QuestionItemDao mapper=MapperFactory.getMapper(sqlSession,QuestionItemDao.class);
            mapper.deleteByQuestionId(questionId);
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
}
