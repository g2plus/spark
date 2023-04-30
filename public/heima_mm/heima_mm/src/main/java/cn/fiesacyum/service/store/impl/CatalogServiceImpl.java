package cn.fiesacyum.service.store.impl;

import cn.fiesacyum.dao.store.CatalogDao;
import cn.fiesacyum.domain.store.Catalog;
import cn.fiesacyum.factory.MapperFactory;
import cn.fiesacyum.service.store.CatalogService;
import cn.fiesacyum.utils.TransactionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CatalogServiceImpl implements CatalogService {
    @Override
    public void save(Catalog catalog) {
        SqlSession sqlSession=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            catalog.setId(UUID.randomUUID().toString());
            catalog.setCreateTime(new Date());
            mapper.save(catalog);
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
    public void delete(Catalog catalog) {
        SqlSession sqlSession=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            mapper.delete(catalog);
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
    public void update(Catalog catalog) {
        SqlSession sqlSession=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            mapper.update(catalog);
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
    public Catalog findById(String id) {
        SqlSession sqlSession=null;
        Catalog catalog=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            catalog=mapper.findById(id);
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return catalog;
    }

    @Override
    public List<Catalog> findAll() {
        SqlSession sqlSession=null;
        List<Catalog> list=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
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
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            Page page = PageHelper.startPage(pageNum, pageSize);
            List<Catalog> all = mapper.findAll();
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
