package cn.fiesacyum.service.store.impl;

import cn.fiesacyum.dao.store.CourseDao;
import cn.fiesacyum.domain.store.Course;
import cn.fiesacyum.factory.MapperFactory;
import cn.fiesacyum.service.store.CourseService;
import cn.fiesacyum.utils.TransactionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CourseServiceImpl implements CourseService {

    @Override
    public void save(Course course) {
        SqlSession sqlSession=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            course.setId(UUID.randomUUID().toString());
            //后台创建的时间,BeanUtil工具不做处理。
            course.setCreateTime(new Date());
            mapper.save(course);
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
    }

    @Override
    public void delete(Course course) {
        SqlSession sqlSession=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            mapper.delete(course);
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
    public void update(Course course) {
        SqlSession sqlSession=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            mapper.update(course);
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
    public Course findById(String id) {
       SqlSession sqlSession=null;
       Course course=null;
       try{
           sqlSession= MapperFactory.getSqlSession();
           CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
           course = mapper.findById(id);
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
        return course;
    }

    @Override
    public List<Course> findAll() {
        SqlSession sqlSession=null;
        List<Course> list=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
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
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            Page page= PageHelper.startPage(pageNum,pageSize);
            List<Course> all= mapper.findAll();
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
}
