package cn.fiesacyum.service.store.impl;

import cn.fiesacyum.dao.store.CompanyDao;
import cn.fiesacyum.domain.store.Company;
import cn.fiesacyum.factory.MapperFactory;
import cn.fiesacyum.service.store.CompanyService;
import cn.fiesacyum.utils.TransactionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class CompanyServiceImpl implements CompanyService {
    @Override
    public void save(Company company) {
        //事务的处理方式！！！
        SqlSession sqlSession=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            //保存
            //前端不进行id的设置，需要对company的id进行设置
            String id= UUID.randomUUID().toString();
            company.setId(id);
            mapper.save(company);
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
    public void delete(Company company) {
        SqlSession sqlSession=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            //删除
            mapper.delete(company);
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
    public void update(Company company) {
        SqlSession sqlSession=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            //更新
            mapper.update(company);
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
    public Company findById(String id) {
        SqlSession sqlSession=null;
        Company company=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            company=mapper.findById(id);
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
        return company;
    }

    @Override
    public List<Company> findAll() {
        SqlSession sqlSession=null;
        List<Company> list=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            //查询
            list=mapper.findAll();
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
        return list;
    }

    @Override
    public PageInfo findAll(int pageNum, int pageSize){
        SqlSession sqlSession=null;
        PageInfo pageInfo=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            //查询
            //拦截处理，确认pageSize与pageNum
            Page page = PageHelper.startPage(pageNum, pageSize);
            List<Company> all = mapper.findAll();
            pageInfo=new PageInfo(all);
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
        return pageInfo;
    }
}
