package cn.fiesacyum.service.system.impl;

import cn.fiesacyum.dao.system.DeptDao;
import cn.fiesacyum.domain.system.Dept;
import cn.fiesacyum.factory.MapperFactory;
import cn.fiesacyum.service.system.DeptService;
import cn.fiesacyum.utils.TransactionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class DeptServiceImpl implements DeptService {
    @Override
    public void save(Dept dept) {
        //事务的处理方式！！！
        SqlSession sqlSession = null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //保存
            //前端不进行id的设置，需要对dept的id进行设置
            String id = UUID.randomUUID().toString();
            dept.setId(id);
            mapper.save(dept);
            //提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public void delete(Dept dept) {
        SqlSession sqlSession = null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //删除
            mapper.delete(dept);
            //提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public void update(Dept dept) {
        SqlSession sqlSession = null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //更新
            mapper.update(dept);
            //提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public Dept findById(String id) {
        SqlSession sqlSession = null;
        Dept dept=null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            dept=mapper.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return dept;
    }

    @Override
    public List<Dept> findAll() {
        SqlSession sqlSession = null;
        List<Dept> list=null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //查询
            list=mapper.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return list;
    }

    @Override
    public PageInfo findAll(int pageNum, int pageSize) {
        SqlSession sqlSession = null;
        PageInfo pageInfo=null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //查询
            //拦截处理，确认pageSize与pageNum
            Page page = PageHelper.startPage(pageNum, pageSize);
            List<Dept> all = mapper.findAll();
            pageInfo = new PageInfo<>(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return pageInfo;
    }
}